package br.com.trasportadora.Events;

import br.com.trasportadora.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyEvents {

    private Company company;
    private Date when;
}
