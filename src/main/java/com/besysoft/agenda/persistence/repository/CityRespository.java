package com.besysoft.agenda.persistence.repository;

import com.besysoft.agenda.persistence.domain.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRespository extends JpaRepository<City, Long> {
    Optional<City> findByName(String name);
}
