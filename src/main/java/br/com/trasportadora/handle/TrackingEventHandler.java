package br.com.trasportadora.handle;

import br.com.trasportadora.domain.TrackingEvent;
import br.com.trasportadora.repository.TrackingEventRepository;
import br.com.trasportadora.service.TrackingEventService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TrackingEventHandler {

    private final TrackingEventService trackingEventService;

    public TrackingEventHandler(TrackingEventService trackingEventService) {
        this.trackingEventService = trackingEventService;
    }

    public Mono<ServerResponse> getStream(ServerRequest serverRequest){
        String trackingId = serverRequest.pathVariable("trackingId");
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(trackingEventService.strems(trackingId), TrackingEvent.class)
                .doOnError(throwable -> new IllegalStateException("Error to response tracking event"));
    }
}
