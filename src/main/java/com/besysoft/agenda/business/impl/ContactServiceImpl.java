package com.besysoft.agenda.business.impl;

import com.besysoft.agenda.business.ContactService;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.helper.ValidateContact;
import com.besysoft.agenda.persistence.domain.Contact;
import com.besysoft.agenda.persistence.domain.ContactState;
import com.besysoft.agenda.persistence.repository.ContactRepository;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    @Override
    public Contact creat(Contact contact) throws InvalidContentException {
        ValidateContact.validate(contact);
        return contactRepository.save(contact);
    }

    @Override
    public ContactDTO deleteById(Long id) throws InvalidContentException {
        ContactDTO contact = ContactDTO.dto(contactRepository.getById(id));
        contactRepository.deleteById(id);
        return contact;
    }

    @Override
    public ContactDTO getById(Long id) throws InvalidContentException {
        Optional<Contact> optional = contactRepository.findById(id);
        if(optional.isPresent()){
            Contact contact = optional.get();
            return ContactDTO.dto(contact);
        }
        throw new InvalidContentException("Invalid contact id");
    }


    @Override
    public ContactDTO setIntoContactState(Long contactId) throws InvalidContentException {
        //Changes contact state into Contact
        if(!this.exists(contactId)){throw new InvalidContentException("Invalid contact Id");}

        Contact contact = contactRepository.findById(contactId).get();
        contact.setState(ContactState.Contact);
        contact.setCreationDate(LocalDateTime.now(ZoneId.of("America/Argentina/Buenos_Aires")));
        contactRepository.save(contact);
        return ContactDTO.dto(contact);
    }

    @Override
    public boolean exists(Long contactId){
        return contactRepository.existsById(contactId);
    }
}
