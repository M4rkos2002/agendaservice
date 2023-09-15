package com.besysoft.agenda.presentation.controller;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.impl.CityServiceImpl;
import com.besysoft.agenda.persistence.domain.City;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path="/city")
public class CityController {

    private final CityServiceImpl cityService;

    public CityController(CityServiceImpl cityService) {
        this.cityService = cityService;
    }

    @PostMapping
    public ResponseEntity<?> creat(@RequestBody City city){
        try{
            City c = cityService.creat(city);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            City c = cityService.getById(id);
            return new ResponseEntity<>(c,HttpStatus.FOUND);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        try{
            City c = cityService.deleteById(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
