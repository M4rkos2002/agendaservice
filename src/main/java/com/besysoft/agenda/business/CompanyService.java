package com.besysoft.agenda.business;

import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.persistence.domain.Company;
import com.besysoft.agenda.presentation.dto.CompanyDTO;
import com.besysoft.agenda.presentation.dto.ContactDTO;

import java.util.List;

public interface CompanyService {

    CompanyDTO creat(Company company) throws InvalidContentException;

    CompanyDTO getById(Long id) throws InvalidContentException;

    CompanyDTO deleteById(Long id) throws InvalidContentException;

    boolean existsById(Long id);
}
