package br.com.trasportadora;

import br.com.trasportadora.domain.Company;
import br.com.trasportadora.domain.Tracking;
import br.com.trasportadora.domain.TrackingEvent;
import br.com.trasportadora.handle.CompanyHandler;
import br.com.trasportadora.handle.TrackingEventHandler;
import br.com.trasportadora.handle.TrackingHandler;
import br.com.trasportadora.repository.CompanyRepository;
import br.com.trasportadora.repository.TrackingEventRepository;
import br.com.trasportadora.repository.TrackingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;
import java.util.UUID;

@SpringBootApplication
public class TrasportadoraApplication implements CommandLineRunner {

	@Autowired
	private CompanyRepository companyRepository;

	@Autowired
	private TrackingRepository trackingRepository;

	@Autowired
	private TrackingEventRepository trackingEventRepository;

	@Autowired
	private CompanyHandler companyHandler;

	@Autowired
	private TrackingHandler trackingHandler;

	@Autowired
	private TrackingEventHandler trackingEventHandler;

	public static void main(String[] args) {
		SpringApplication.run(TrasportadoraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Tracking tracking = new Tracking();
		tracking.setId(UUID.randomUUID().toString());

		trackingRepository.deleteAll()
			.thenMany(
					Flux.just(tracking)
					.map(model -> model)
					.flatMap(trackingRepository::save))
			.subscribe(System.out::println);

		TrackingEvent trackingEvent = new TrackingEvent(UUID.randomUUID().toString(),tracking, "Teste de tracking", "Enviado", LocalDateTime.now());
		TrackingEvent trackingEvent2 = new TrackingEvent(UUID.randomUUID().toString(), tracking, "Teste de tracking", "Na Rua Tal", LocalDateTime.now());
		trackingEventRepository.deleteAll()
			.thenMany(
					Flux.just(trackingEvent, trackingEvent2)
					.map(model -> model)
					.flatMap(trackingEventRepository::save))
			.subscribe(System.out::println);

		companyRepository.deleteAll()
				.thenMany(
						Flux.just("Loggi", "Transfolha", "KingFrete", "Alguma", "SempreEntregue")
						.map(model -> new Company(UUID.randomUUID().toString(), model, true, ""))
						.flatMap(companyRepository::save))
				.subscribe(System.out::println);
	}

	@Bean
	RouterFunction<?> routes(CompanyHandler companyHandler){
		return RouterFunctions.route(
				RequestPredicates.GET("/companies"), companyHandler::allCompany)
				.andRoute(RequestPredicates.GET("/companies/{id}"), companyHandler::byId)
				.andRoute(RequestPredicates.GET("/companies/{id}/events"), companyHandler::events);
	}

	@Bean
	RouterFunction<?> trackingRoutes(TrackingHandler trackingHandler){
		return RouterFunctions.route(
				RequestPredicates.GET("/trackings"), trackingHandler::getAll);
	}

	@Bean
	RouterFunction<?> trackingEventsRoutes(TrackingEventHandler trackingEventHandler){
		return RouterFunctions.route(
				RequestPredicates.GET("/tracking-event/{trackingId}/events"), trackingEventHandler::getStream);
	}
}
