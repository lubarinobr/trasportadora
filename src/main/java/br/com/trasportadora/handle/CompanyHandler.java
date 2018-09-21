package br.com.trasportadora.handle;

import br.com.trasportadora.Events.CompanyEvents;
import br.com.trasportadora.domain.Company;
import br.com.trasportadora.service.CompanyService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class CompanyHandler {

    private final CompanyService companyService;

    public CompanyHandler(CompanyService companyService) {
        this.companyService = companyService;
    }

    public Mono<ServerResponse> allCompany(ServerRequest serverRequest){
        return ServerResponse.ok()
                .body(companyService.getAll(), Company.class)
                .doOnError(throwable -> new IllegalStateException("Error to consume all companies"));
    }

    public Mono<ServerResponse> byId(ServerRequest serverRequest){
        String companyId = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .body(companyService.getById(companyId), Company.class)
                .doOnError(throwable -> new IllegalStateException("Erro to consume company by id"));
    }

    public Mono<ServerResponse> events(ServerRequest serverRequest){
        String companyId = serverRequest.pathVariable("id");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(companyService.streams(companyId), CompanyEvents.class)
                .doOnError(throwable -> new IllegalStateException("Error to consume strem of company events"));
    }

}
