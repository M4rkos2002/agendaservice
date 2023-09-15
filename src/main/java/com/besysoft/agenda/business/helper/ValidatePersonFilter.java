package com.besysoft.agenda.business.helper;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.presentation.dto.PersonFilter;

import java.util.List;

public class ValidatePersonFilter {

    public static void validate(PersonFilter filter) throws InvalidContentException {
        validateAges(filter.getAgeFrom(), filter.getAgeTo());
    }


    public static void validateAges(Integer from, Integer to) throws InvalidContentException {
        if(from > to ){throw new InvalidContentException("Invalid age");}
    }

}
