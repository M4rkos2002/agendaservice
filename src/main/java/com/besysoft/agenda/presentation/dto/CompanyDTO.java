package com.besysoft.agenda.presentation.dto;

import com.besysoft.agenda.persistence.domain.Company;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CompanyDTO {

    private Long id;
    private String name;
    private Long cityId;

    public static CompanyDTO dto(Company company){
        CompanyDTO result = new CompanyDTO();
        result.setId(company.getId());
        result.setName(company.getName());
        result.setCityId(company.getCityId());
        return result;
    }


}
