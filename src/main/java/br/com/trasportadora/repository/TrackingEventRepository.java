package br.com.trasportadora.repository;

import br.com.trasportadora.domain.TrackingEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface TrackingEventRepository extends ReactiveMongoRepository<TrackingEvent, String> {

    Mono<TrackingEvent> findFirstByTrackingOrderByWhenDesc(String tracking);
}
