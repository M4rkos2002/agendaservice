package com.besysoft.agenda.business.impl;

import com.besysoft.agenda.business.PersonService;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.helper.ValidatePersonContent;
import com.besysoft.agenda.business.helper.ValidatePersonFilter;
import com.besysoft.agenda.persistence.domain.Agenda;
import com.besysoft.agenda.persistence.domain.Contact;
import com.besysoft.agenda.persistence.domain.ContactState;
import com.besysoft.agenda.persistence.domain.Person;
import com.besysoft.agenda.persistence.repository.AgendaRepository;
import com.besysoft.agenda.persistence.repository.PersonRepository;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import com.besysoft.agenda.presentation.dto.PersonDTO;
import com.besysoft.agenda.presentation.dto.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.besysoft.agenda.persistence.specification.PersonSpecification;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {


    private final PersonRepository personRepository;
    private final AgendaRepository agendaRepository;
    private final AgendaServiceImpl agendaService;

    public PersonServiceImpl(PersonRepository personRepository, AgendaRepository agendaRepository, AgendaServiceImpl agendaService) {
        this.personRepository = personRepository;
        this.agendaRepository = agendaRepository;
        this.agendaService = agendaService;
    }

    @Override
    public PersonDTO creat(Person person) throws InvalidContentException {
        ValidatePersonContent.validateCreat(person);
        Person p = personRepository.save(person);
        return PersonDTO.dto(p);
    }

    @Override
    public PersonDTO getById(Long id) throws InvalidContentException {
        Optional<Person> optional = personRepository.findById(id);
        if(optional.isPresent()){
            Person person = optional.get();
            return PersonDTO.dto(person);
        }
        throw new InvalidContentException("Invalid person Id");
    }

    @Override
    public PersonDTO deleteById(Long id) throws InvalidContentException {
        PersonDTO p = this.getById(id);
        personRepository.deleteById(id);
        return p;
    }

    @Override
    public Page<Person> getAll(PersonFilter filter, Pageable pageable) throws InvalidContentException {
        //Generates a page of Persons with specified attributes in filter

        ValidatePersonFilter.validate(filter);  //validates that filter has valid content

        final PersonSpecification specification = new PersonSpecification(filter);
        List<Person> persons = personRepository.findAll(specification);

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

        List<Person> pagePersons;

        if (persons.size() < startItem) {
            pagePersons = List.of();
        } else {
            int toIndex = Math.min(startItem + pageSize, persons.size());
            pagePersons = persons.subList(startItem, toIndex);
        }

        return new PageImpl<>(pagePersons, pageable, persons.size());
    }

    @Override
    public ContactDTO beOpportunity(Long personId, Long agendaId) throws InvalidContentException {
        //Generates a contact with an Opportunity state
        boolean isPerson = personRepository.existsById(personId);
        boolean isAgenda = agendaRepository.existsById(agendaId);
        if(!isPerson || !isAgenda){throw new InvalidContentException("Invalid Ids");}

        Agenda agenda = agendaRepository.findById(agendaId).get();

        Contact contact = new Contact();
        contact.setPersonId(personId);
        contact.setAgenda(agenda);
        contact.setState(ContactState.Opportunity); //Sets contact into opportunity
        contact.setCreationDate(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));

        return agendaService.setContact(contact, agendaId);
    }


}
