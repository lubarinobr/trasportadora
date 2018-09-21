package br.com.trasportadora.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PackageInfo {

    @Id
    private String id;
    private Double height;
    private Double width;
    private Double lenght;
    private String description;
}
