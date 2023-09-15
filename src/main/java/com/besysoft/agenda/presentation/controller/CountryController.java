package com.besysoft.agenda.presentation.controller;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.impl.CityServiceImpl;
import com.besysoft.agenda.business.impl.CountryServiceImpl;
import com.besysoft.agenda.persistence.domain.City;
import com.besysoft.agenda.persistence.domain.Country;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/country")
public class CountryController {


    private final CountryServiceImpl countryService;

    public CountryController(CountryServiceImpl countryService) {
        this.countryService = countryService;
    }

    @PostMapping
    public ResponseEntity<?> creat(@RequestBody Country country){
        Country c = countryService.creat(country);
        return new ResponseEntity<>(c, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            Country c = countryService.getById(id);
            return new ResponseEntity<>(c,HttpStatus.FOUND);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        countryService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
