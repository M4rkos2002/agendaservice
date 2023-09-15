package com.besysoft.agenda.business.helper;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Person;

public class ValidatePersonContent {

    public static void validateCreat(Person person) throws InvalidContentException {
        ValidatePersonContent.validateName(person.getName());
        ValidatePersonContent.validateAge(person.getAge());
    }


    public static void validateName(String name) throws InvalidContentException {
        if(name.isEmpty()){throw new InvalidContentException("Invalid name");}
    }

    public static void validateAge(Integer age) throws InvalidContentException {
        if(age != null || age < 0){throw new InvalidContentException("Invalid age");}
    }
}
