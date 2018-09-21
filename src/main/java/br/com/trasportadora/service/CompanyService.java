package br.com.trasportadora.service;

import br.com.trasportadora.Events.CompanyEvents;
import br.com.trasportadora.domain.Company;
import br.com.trasportadora.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Flux<Company> getAll(){
        return companyRepository.findAll();
    }

    public Mono<Company> getById(String id){
        return companyRepository.findById(id);
    }

    public Flux<CompanyEvents> streams(String id){
        return getById(id).flatMapMany(company -> {
            Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
            Flux<CompanyEvents> events = Flux.fromStream(
                    Stream.generate(() -> new CompanyEvents(company, new Date())));
            return Flux.zip(interval, events).map(Tuple2::getT2);
        });
    }
}
