package com.besysoft.agenda.business.impl;


import com.besysoft.agenda.persistence.repository.UserRepository;
import com.besysoft.agenda.business.UserService;
import com.besysoft.agenda.persistence.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String username) {
        return (User) userRepository.findByEmail(username).orElse(null);
    }
}
