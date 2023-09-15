package com.besysoft.agenda.presentation.controller;

import com.besysoft.agenda.business.exception.EmptyListException;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.impl.AgendaServiceImpl;
import com.besysoft.agenda.persistence.domain.Agenda;
import com.besysoft.agenda.persistence.domain.ContactState;
import com.besysoft.agenda.presentation.dto.AgendaDTO;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {

    private final AgendaServiceImpl agendaService;

    public AgendaController(AgendaServiceImpl agendaService) {
        this.agendaService = agendaService;
    }


    @PostMapping
    public ResponseEntity<?> creat(@RequestBody Agenda agenda){
        try{
            AgendaDTO dto = agendaService.creat(agenda);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        try{
            List<AgendaDTO> agendas = agendaService.getAll();
            return new ResponseEntity<>(agendas, HttpStatus.OK);
        }
        catch (EmptyListException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping(path = "/byState/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long agendaId){
        try{
            AgendaDTO dto = agendaService.getById(agendaId);
            return new ResponseEntity<>(dto, HttpStatus.FOUND);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getAllContactsByState(@PathVariable("id") Long agendaId, @RequestParam("state")ContactState state){
        try{
            List<ContactDTO> contacts = agendaService.getAllContacts(agendaId, state);
            return new ResponseEntity<>(contacts, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
