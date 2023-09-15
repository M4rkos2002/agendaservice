package com.besysoft.agenda.business;

import com.besysoft.agenda.persistence.domain.User;
public interface UserService {

    public void saveUser(User user);

    User findByEmail(String username);
}
