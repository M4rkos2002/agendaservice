package com.besysoft.agenda.business;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.City;

public interface CityService {

    City creat(City city) throws InvalidContentException;

    City getById(Long id) throws InvalidContentException;

    City deleteById(Long id) throws InvalidContentException;

    boolean existsById(Long id);
}
