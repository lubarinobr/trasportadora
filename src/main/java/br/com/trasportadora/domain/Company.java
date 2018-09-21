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
public class Company {

    @Id
    private String idtCompany;
    private String name;
    private Boolean active;
    private String hashIdentify;
}
