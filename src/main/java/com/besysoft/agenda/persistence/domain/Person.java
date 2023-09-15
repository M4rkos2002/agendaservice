package com.besysoft.agenda.persistence.domain;

import lombok.Getter;
import lombok.Setter;


import javax.persistence.*;

@Getter
@Setter

@Entity
public class Person {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Long cityId;

    @Column
    private Integer age;

    public Person(){};


}
