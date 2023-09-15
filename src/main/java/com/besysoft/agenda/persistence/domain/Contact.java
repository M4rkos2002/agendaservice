package com.besysoft.agenda.persistence.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long personId;

    @Column
    private LocalDateTime creationDate;

    @Column
    private ContactState state;

    @ManyToOne(cascade = CascadeType.ALL)
    private Agenda agenda;

    public Contact(){}

    public Contact(Long id, Long agendaId, Long personId, LocalDateTime creationDate, ContactState state, Agenda agenda) {
        this.id = id;
        this.personId = personId;
        this.creationDate = creationDate;
        this.state = state;
        this.agenda = agenda;
    }
}
