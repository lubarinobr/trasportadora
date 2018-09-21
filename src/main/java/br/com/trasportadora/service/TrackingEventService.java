package br.com.trasportadora.service;

import br.com.trasportadora.domain.TrackingEvent;
import br.com.trasportadora.repository.TrackingEventRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.stream.Stream;

@Service
public class TrackingEventService {

    private final TrackingEventRepository trackingEventRepository;

    public TrackingEventService(TrackingEventRepository trackingEventRepository) {
        this.trackingEventRepository = trackingEventRepository;
    }

    public Mono<TrackingEvent> getLastByTrackingId(final String trackingId){
        return this.trackingEventRepository.findFirstByTrackingOrderByWhenDesc(trackingId);
    }

    public Flux<TrackingEvent> strems(final String tracking){
        return getLastByTrackingId(tracking).flatMapMany(trackingEvent -> {
           Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
           Flux<TrackingEvent> events = Flux.fromStream(
                   Stream.generate(() -> trackingEvent));
           return Flux.zip(interval, events).map(Tuple2::getT2);
        });
    }
}
