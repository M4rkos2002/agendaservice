package com.besysoft.agenda.business;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Person;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import com.besysoft.agenda.presentation.dto.PersonDTO;
import com.besysoft.agenda.presentation.dto.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PersonService {

    PersonDTO creat(Person person) throws InvalidContentException;

    PersonDTO getById(Long id) throws InvalidContentException;

    PersonDTO deleteById(Long id) throws InvalidContentException;

    Page<Person> getAll(PersonFilter filter, Pageable pageable) throws InvalidContentException;

    ContactDTO beOpportunity(Long personId, Long agendaId) throws InvalidContentException;
}
