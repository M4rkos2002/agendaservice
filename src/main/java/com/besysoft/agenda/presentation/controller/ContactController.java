package com.besysoft.agenda.presentation.controller;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.impl.ContactServiceImpl;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
public class ContactController {

    private final ContactServiceImpl contactService;

    public ContactController(ContactServiceImpl contactService) {
        this.contactService = contactService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable("id") Long id){
        try{
            ContactDTO dto = contactService.getById(id);
            return new ResponseEntity<>(dto, HttpStatus.FOUND);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping({"/{id}"})
    public ResponseEntity<?> setIntoContact(@PathVariable("id") Long contactId){
        try{
            ContactDTO dto = contactService.setIntoContactState(contactId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
}
