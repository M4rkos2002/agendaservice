package com.besysoft.agenda.presentation.controller;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.impl.CompanyServiceImpl;
import com.besysoft.agenda.persistence.domain.Company;
import com.besysoft.agenda.presentation.dto.CompanyDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {

    private final CompanyServiceImpl companyService;

    public CompanyController(CompanyServiceImpl companyService) {
        this.companyService = companyService;
    }


    @PostMapping
    public ResponseEntity<?> creat(@RequestBody Company company){
        try{
            CompanyDTO c = companyService.creat(company);
            return new ResponseEntity<>(c, HttpStatus.CREATED);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Long id){
        try{
            CompanyDTO c = companyService.getById(id);
            return new ResponseEntity<>(c, HttpStatus.FOUND);
        }
        catch (InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        try{
            CompanyDTO c = companyService.deleteById(id);
            return new ResponseEntity<>(c, HttpStatus.OK);
        }
        catch(InvalidContentException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

}
