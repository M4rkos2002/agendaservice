package com.besysoft.agenda.business;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Country;

public interface CountryService {

    Country creat(Country country);

    Country getById(Long id) throws InvalidContentException;

    boolean existsById(Long id);

    void deleteById(Long id);
}
