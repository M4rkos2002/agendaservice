package com.besysoft.agenda.presentation.controller;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.impl.PersonServiceImpl;
import com.besysoft.agenda.persistence.domain.Person;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import com.besysoft.agenda.presentation.dto.PersonDTO;
import com.besysoft.agenda.presentation.dto.PersonFilter;
import org.apache.coyote.Response;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonServiceImpl personService;

    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }


    @PostMapping
    public ResponseEntity<?> creat(@RequestBody Person person){
        try{
            PersonDTO dto = personService.creat(person);
            return new ResponseEntity<>(dto, HttpStatus.CREATED);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long personId){
        try{
            PersonDTO dto = personService.getById(personId);
            return new ResponseEntity<>(dto, HttpStatus.FOUND);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long personId){
        try{
            PersonDTO dto = personService.deleteById(personId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping
    public ResponseEntity<?> getAll(@RequestParam(required = false,name ="ageFrom") Integer ageFrom,
                                    @RequestParam(required = false,name="ageTo") Integer ageTo,
                                    @RequestParam(required = false,name="name") String name,
                                    @RequestParam(required = false,name="cities") List<Long> cities,
                                    @RequestParam(name="page", defaultValue= "0" ) int page,
                                    @RequestParam(name="size", defaultValue="10") int size){
        PersonFilter filter = new PersonFilter(ageFrom,ageTo,name,cities);
        Pageable pages = PageRequest.of(page, size);
        try {
            Page<Person> result = personService.getAll(filter, pages);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (InvalidContentException e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> setAsOpportunity(@PathVariable("id")Long personId, @RequestParam(name="agenda")Long agendaId){
        try{
            ContactDTO dto = personService.beOpportunity(personId, agendaId);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }


}
