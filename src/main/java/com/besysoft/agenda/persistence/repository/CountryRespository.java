package com.besysoft.agenda.persistence.repository;

import com.besysoft.agenda.persistence.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRespository extends JpaRepository<Country, Long> {
}
