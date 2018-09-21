package br.com.trasportadora.handle;

import br.com.trasportadora.domain.Tracking;
import br.com.trasportadora.service.TrackingService;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
public class TrackingHandler {

    private final TrackingService trackingService;

    public TrackingHandler(TrackingService trackingService) {
        this.trackingService = trackingService;
    }

    public Mono<ServerResponse> getAll(ServerRequest serverRequest){
        return ServerResponse.ok()
                .body(trackingService.getAll(), Tracking.class)
                .doOnError(throwable -> new IllegalStateException("Error to consume all Tracking"));
    }
}
