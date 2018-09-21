package br.com.trasportadora.service;

import br.com.trasportadora.domain.Tracking;
import br.com.trasportadora.repository.TrackingRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class TrackingService {

    private final TrackingRepository trackingRepository;

    public TrackingService(TrackingRepository trackingRepository) {
        this.trackingRepository = trackingRepository;
    }

    public Flux<Tracking> getAll(){
        return trackingRepository.findAll();
    }
}
