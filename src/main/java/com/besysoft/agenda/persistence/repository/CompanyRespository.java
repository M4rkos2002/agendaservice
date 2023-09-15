package com.besysoft.agenda.persistence.repository;

import com.besysoft.agenda.persistence.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRespository extends JpaRepository<Company, Long> {
}
