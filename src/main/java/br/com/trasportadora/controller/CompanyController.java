package br.com.trasportadora.controller;

import br.com.trasportadora.Events.CompanyEvents;
import br.com.trasportadora.domain.Company;
import br.com.trasportadora.service.CompanyService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/companies")
    public Flux<Company> getAll(){
        return companyService.getAll();
    }

    @GetMapping("/companies/{id}")
    public Mono<Company> byId(@PathVariable String id){
        return companyService.getById(id);
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/companies/{id}/events")
    public Flux<CompanyEvents> eventsOfStream(@PathVariable String id){
        return companyService.streams(id);
    }
}
