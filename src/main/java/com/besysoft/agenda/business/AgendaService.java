package com.besysoft.agenda.business;

import com.besysoft.agenda.business.exception.EmptyListException;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Agenda;
import com.besysoft.agenda.persistence.domain.Contact;
import com.besysoft.agenda.persistence.domain.ContactState;
import com.besysoft.agenda.presentation.dto.AgendaDTO;
import com.besysoft.agenda.presentation.dto.ContactDTO;

import java.util.List;

public interface AgendaService {

    AgendaDTO getById(Long id) throws InvalidContentException;

    List<AgendaDTO> getAll() throws EmptyListException;

    AgendaDTO creat(Agenda agenda) throws InvalidContentException;

    List<ContactDTO> getAllContacts(Long agendaId, ContactState state) throws InvalidContentException;

    ContactDTO setContact(Contact contact, Long agendaId) throws InvalidContentException;

    boolean exists(Long contactId);
}
