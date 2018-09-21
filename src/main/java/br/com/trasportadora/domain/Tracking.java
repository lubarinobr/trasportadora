package br.com.trasportadora.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Collection;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tracking {

    @Id
    private String id;
    private Company company;
    private PackageInfo packageInfo;
    private LocalDateTime date_start_delivery;
    private LocalDateTime date_end_delivery;
    private Collection<TrackingEvent> trackingEvents;
}
