package com.besysoft.agenda.business.impl;

import com.besysoft.agenda.business.CityService;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.helper.ValidateCity;
import com.besysoft.agenda.persistence.domain.City;
import com.besysoft.agenda.persistence.repository.CityRespository;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CityServiceImpl implements CityService {

    private final CityRespository cityRespository;
    private final CountryServiceImpl countryService;

    public CityServiceImpl(CityRespository cityRespository, CountryServiceImpl countryService) {
        this.cityRespository = cityRespository;
        this.countryService = countryService;
    }


    @Override
    public City creat(City city) throws InvalidContentException {
        ValidateCity.validateCreat(city, countryService.existsById(city.getCountryId()));
        return cityRespository.save(city);
    }

    @Override
    public City getById(Long id) throws InvalidContentException {
        Optional<City> optional = cityRespository.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        throw new InvalidContentException("Invalid city id");
    }

    @Override
    public City deleteById(Long id) throws InvalidContentException {
        if(this.existsById(id)){
            City city = this.getById(id);
            cityRespository.deleteById(id);
            return city;
        }
        throw new InvalidContentException("Invalid city Id");
    }

    @Override
    public boolean existsById(Long id){
        return cityRespository.existsById(id);
    }

}
