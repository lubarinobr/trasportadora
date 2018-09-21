package br.com.trasportadora.repository;

import br.com.trasportadora.domain.Company;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CompanyRepository extends ReactiveMongoRepository<Company, String> {
}
