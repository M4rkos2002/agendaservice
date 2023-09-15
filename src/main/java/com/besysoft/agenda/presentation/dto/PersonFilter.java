package com.besysoft.agenda.presentation.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter

public class PersonFilter {

    private Integer ageFrom;
    private Integer ageTo;
    private String name;
    private List<Long> cities;

    public PersonFilter(Integer ageFrom, Integer ageTo, String name, List<Long> cities) {
        this.ageFrom = ageFrom;
        this.ageTo = ageTo;
        this.name = name;
        this.cities = cities;
    }
}
