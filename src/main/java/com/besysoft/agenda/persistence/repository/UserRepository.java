package com.besysoft.agenda.persistence.repository;

import com.besysoft.agenda.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<Object> findByEmail(String eMail);

}
