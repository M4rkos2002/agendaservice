package com.besysoft.agenda.persistence.repository;

import com.besysoft.agenda.persistence.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
