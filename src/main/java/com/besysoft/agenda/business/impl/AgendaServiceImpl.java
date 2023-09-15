package com.besysoft.agenda.business.impl;

import com.besysoft.agenda.business.AgendaService;
import com.besysoft.agenda.business.exception.EmptyListException;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.helper.ValidateAgenda;
import com.besysoft.agenda.business.helper.ValidateContact;
import com.besysoft.agenda.persistence.domain.Agenda;
import com.besysoft.agenda.persistence.domain.Contact;
import com.besysoft.agenda.persistence.domain.ContactState;
import com.besysoft.agenda.persistence.repository.AgendaRepository;
import com.besysoft.agenda.presentation.dto.AgendaDTO;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;

    private final CompanyServiceImpl companyService;
    private final ContactServiceImpl contactService;

    public AgendaServiceImpl(AgendaRepository agendaRepository, CompanyServiceImpl companyService, ContactServiceImpl contactService) {
        this.agendaRepository = agendaRepository;
        this.companyService = companyService;
        this.contactService = contactService;
    }

    @Override
    public AgendaDTO getById(Long id) throws InvalidContentException {
        Optional<Agenda> optional = agendaRepository.findById(id);
        if(optional.isPresent()){
            Agenda agenda = optional.get();
            return AgendaDTO.dto(agenda);
        }
        throw new InvalidContentException("Invalid agenda id");
    }

    @Override
    public List<AgendaDTO> getAll() throws EmptyListException {
        List<Agenda> agendas = agendaRepository.findAll();
        if(agendas.isEmpty()){
            throw new EmptyListException("There are no agendas");
        }
        List<AgendaDTO> dtos = new ArrayList<>();
        for(Agenda a:agendas){
            dtos.add(AgendaDTO.dto(a));
        }
        return dtos;
    }

    @Override
    public AgendaDTO creat(Agenda agenda) throws InvalidContentException {
        ValidateAgenda.validate(agenda, companyService.existsById(agenda.getCompanyId()));
        agendaRepository.save(agenda);
        return AgendaDTO.dto(agenda);
    }

    @Override
    public List<ContactDTO> getAllContacts(Long agendaId, ContactState state) throws InvalidContentException {
        AgendaDTO dto = this.getById(agendaId);
        List<ContactDTO> contacts = new ArrayList<>();
        for(ContactDTO c:dto.getContacts()){
            if(c.getState().equals(state)){
                contacts.add(c);
            }
        }
        return contacts;
    }


    @Override
    public ContactDTO setContact(Contact contact, Long agendaId) throws InvalidContentException {
        //adds a contact in contacts list from an agenda for first time
        if(this.exists(agendaId)){
            Agenda agenda = agendaRepository.findById(agendaId).get();
            List<Contact> contacts = agenda.getContacts();
            if(ValidateContact.containsContact(contact, contacts)){
                Contact saved = contactService.creat(contact); //saves the contact on database
                contacts.add(saved);
                agenda.setContacts(contacts);
                agendaRepository.save(agenda);
                return ContactDTO.dto(saved);
            }
            throw new InvalidContentException("Contact already Exists");
        }
        throw new InvalidContentException("Invalid agenda Id");
    }

    @Override
    public boolean exists(Long contactId){
        return agendaRepository.existsById(contactId);
    }

}
