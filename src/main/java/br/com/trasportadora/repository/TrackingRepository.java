package br.com.trasportadora.repository;

import br.com.trasportadora.domain.Tracking;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface TrackingRepository extends ReactiveMongoRepository<Tracking, String> {
}
