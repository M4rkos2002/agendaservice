package com.besysoft.agenda.business.impl;

import com.besysoft.agenda.business.CompanyService;
import com.besysoft.agenda.business.exception.InvalidContentException;
import com.besysoft.agenda.business.helper.ValidateCompanyContent;
import com.besysoft.agenda.persistence.domain.Company;
import com.besysoft.agenda.persistence.domain.Contact;
import com.besysoft.agenda.persistence.domain.ContactState;
import com.besysoft.agenda.persistence.repository.AgendaRepository;
import com.besysoft.agenda.persistence.repository.CompanyRespository;
import com.besysoft.agenda.persistence.repository.ContactRepository;
import com.besysoft.agenda.presentation.dto.CompanyDTO;
import com.besysoft.agenda.presentation.dto.ContactDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRespository companyRespository;

    public CompanyServiceImpl(CompanyRespository companyRespository) {
        this.companyRespository = companyRespository;
    }


    @Override
    public CompanyDTO creat(Company company) throws InvalidContentException {
        ValidateCompanyContent.validate(company);
        Company c = companyRespository.save(company);
        return CompanyDTO.dto(c);
    }

    @Override
    public CompanyDTO getById(Long id) throws InvalidContentException {
        Optional<Company> optional = companyRespository.findById(id);
        if(optional.isPresent()){
            Company company = optional.get();
            return CompanyDTO.dto(company);
        }
        throw new InvalidContentException("Invalid company Id");
    }

    @Override
    public CompanyDTO deleteById(Long id) throws InvalidContentException {
        CompanyDTO c = this.getById(id);
        companyRespository.deleteById(id);
        return c;
    }

    @Override
    public boolean existsById(Long id) {
        return companyRespository.existsById(id);
    }
}
