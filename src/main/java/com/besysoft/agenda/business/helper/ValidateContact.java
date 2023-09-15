package com.besysoft.agenda.business.helper;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Agenda;
import com.besysoft.agenda.persistence.domain.Contact;

import java.time.LocalDateTime;
import java.util.List;

public class ValidateContact {

    public static void validate(Contact contact) throws InvalidContentException {
        ValidateContact.validatePersonId(contact.getPersonId());
        ValidateContact.validateAgendaId(contact.getAgenda());
        ValidateContact.validateDate(contact.getCreationDate());
    }

    public static void validateAgendaId(Agenda agenda) throws InvalidContentException {
        if(agenda.equals(null)){throw new InvalidContentException("Invalid agenda Id");
        }
    }

    public static void validatePersonId(Long id) throws InvalidContentException {
        if(id == null){throw new InvalidContentException("Invalid person Id");}
    }

    public static void validateDate(LocalDateTime date) throws InvalidContentException {
        if(date == null){throw new InvalidContentException("Invalid date");
        }
    }

    public static boolean containsContact(Contact contact, List<Contact> contacts){
        for(Contact c: contacts){
            if(ValidateContact.compareContacts(c,contact)){
                return true;
            }
        }
        return false;
    }

    public static boolean compareContacts(Contact c1, Contact c2){
        return c1.getAgenda().equals(c2.getAgenda()) &&
                c1.getState().equals(c2.getState()) &&
                c1.getPersonId().equals(c2.getPersonId());
    }

}
