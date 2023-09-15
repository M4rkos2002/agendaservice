package com.besysoft.agenda.business.helper;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Agenda;

public class ValidateAgenda {

    public static void validate(Agenda agenda, boolean companyExists) throws InvalidContentException {
        ValidateAgenda.validateCompanyId(agenda.getId(), companyExists);
    }

    public static void validateCompanyId(Long id, boolean exists) throws InvalidContentException {
        //checks if id != null and if company exists
        if(id == null || !exists){throw new InvalidContentException("Invalid company Id");
        }
    }

}
