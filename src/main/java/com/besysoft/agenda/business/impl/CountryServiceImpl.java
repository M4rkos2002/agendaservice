package com.besysoft.agenda.business.impl;

import com.besysoft.agenda.business.CountryService;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Country;
import com.besysoft.agenda.persistence.repository.CountryRespository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRespository countryRespository;

    public CountryServiceImpl(CountryRespository countryRespository) {
        this.countryRespository = countryRespository;
    }

    @Override
    public Country creat(Country country) {
        return countryRespository.save(country);
    }

    @Override
    public Country getById(Long id) throws InvalidContentException {
        Optional<Country> optional = countryRespository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new InvalidContentException("Invalid country Id");
    }

    @Override
    public boolean existsById(Long id) {
        return countryRespository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if(this.existsById(id)){
            countryRespository.deleteById(id);
        }
    }
}
