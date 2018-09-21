package br.com.trasportadora.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrackingEvent {

    @Id
    @Field("idt_tracking_event")
    private String id;
    @DBRef
    private Tracking tracking;
    private String description;
    private String status;
    private LocalDateTime when;
}
