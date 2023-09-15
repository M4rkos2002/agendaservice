package com.besysoft.agenda.presentation.dto;

import com.besysoft.agenda.persistence.domain.Contact;
import com.besysoft.agenda.persistence.domain.ContactState;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter

public class ContactDTO {

    private Long id;
    private Long personId;
    private Long agendaId;
    private LocalDateTime creationDate;
    private ContactState state;

    public static ContactDTO dto(Contact contact){
        ContactDTO result = new ContactDTO();
        result.setId(contact.getId());
        result.setAgendaId(contact.getAgenda().getId());
        result.setPersonId(contact.getPersonId());
        result.setCreationDate(contact.getCreationDate());
        result.setState(contact.getState());
        return result;
    }
}
