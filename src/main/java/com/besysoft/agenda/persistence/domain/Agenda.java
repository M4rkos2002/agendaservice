package com.besysoft.agenda.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter

@Entity
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long companyId;

    @Column
    private String topic;

    @OneToMany(targetEntity = Contact.class ,cascade = CascadeType.ALL)
    private List<Contact> contacts;

    public Agenda(){};
}
