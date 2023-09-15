package com.besysoft.agenda.presentation.dto;

import com.besysoft.agenda.persistence.domain.Person;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PersonDTO {

    private Long id;
    private String name;
    private Long cityId;
    private Integer age;

    public static PersonDTO dto(Person person){
        PersonDTO result = new PersonDTO();
        result.setId(person.getId());
        result.setName(person.getName());
        result.setCityId(person.getCityId());
        result.setAge(person.getAge());
        return result;
    }
}
