package com.besysoft.agenda.presentation.dto;

import com.besysoft.agenda.persistence.domain.Agenda;
import com.besysoft.agenda.persistence.domain.Contact;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter

public class AgendaDTO {

    private Long id;
    private Long companyId;
    private String topic;
    private List<ContactDTO> contacts;

    public static AgendaDTO dto(Agenda agenda){
        AgendaDTO result = new AgendaDTO();
        result.setId(agenda.getId());
        result.setTopic(agenda.getTopic());
        result.setCompanyId(agenda.getCompanyId());
        List<ContactDTO> contactList = new ArrayList<>();
        for(Contact c:agenda.getContacts()){
            contactList.add(ContactDTO.dto(c));
        }
        result.setContacts(contactList);
        return result;
    }
}
