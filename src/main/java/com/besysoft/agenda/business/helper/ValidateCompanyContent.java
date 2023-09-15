package com.besysoft.agenda.business.helper;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Company;
import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

public class ValidateCompanyContent {

    public static void validate(Company company) throws InvalidContentException {
        ValidateCompanyContent.validateCity(company.getCityId());
        ValidateCompanyContent.validateName(company.getName());
    }

    public static void validateCity(Long cityId) throws InvalidContentException {
        if(cityId == null){throw new InvalidContentException("Invalid city id");}
    }

    public static void validateName(String name) throws InvalidContentException {
        if(name.isEmpty()){throw new InvalidContentException("Invalid name");}
    }
}
