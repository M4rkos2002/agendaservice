package com.besysoft.agenda.business;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Contact;
import com.besysoft.agenda.presentation.dto.ContactDTO;

public interface ContactService {

    Contact creat(Contact contact) throws InvalidContentException;

    ContactDTO deleteById(Long id) throws InvalidContentException;

    ContactDTO getById(Long id) throws  InvalidContentException;

    ContactDTO setIntoContactState(Long contactId) throws InvalidContentException;

    boolean exists(Long contactId);

}
