package com.besysoft.agenda.business.helper;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.City;
import com.besysoft.agenda.persistence.domain.Country;

import java.util.List;
import java.util.Objects;

public class ValidateCity {

    public static void validateCreat(City city, boolean existsCountry) throws InvalidContentException {
        ValidateCity.validateName(city.getName());
        ValidateCity.validateCountry(city.getCountryId(), existsCountry);
    }

    public static void validateName(String name) throws InvalidContentException {
        if(name.isEmpty()){throw new InvalidContentException("Invalid name");
        }
    }

    public static void validateCountry(Long countryId, boolean exists) throws InvalidContentException {
        if (exists || countryId != null){
            throw new InvalidContentException("Invalid country Id");
        }
    }
}
