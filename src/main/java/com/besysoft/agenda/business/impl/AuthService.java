package com.besysoft.agenda.business.impl;


import com.besysoft.agenda.persistence.domain.User;
import com.besysoft.agenda.presentation.dto.AuthenticationRequest;
import com.besysoft.agenda.persistence.repository.UserRepository;
import com.besysoft.agenda.presentation.dto.AuthenticationResponse;
import com.besysoft.agenda.presentation.dto.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse signUp(SignUpForm signUpForm) {

        User user = new User();
        user.setEmail(signUpForm.getEmail());
        user.setPassword(signUpForm.getPassword());
        AuthenticationResponse response = AuthenticationResponse.builder().token(jwtService.generateToken(user)).build();

        userRepository.save(user);
        return response;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        User user = (User) userRepository.findByEmail( request.getUsername() ).orElse( null );

        if (user == null) return null;

        if (request.getPassword().equals(user.getPassword()) && request.getUsername().equals(user.getEmail()) ){

            var jwtToken = jwtService.generateToken(user);

            AuthenticationResponse response = AuthenticationResponse.builder().token(jwtToken).build();;

            return response;
        }

        return null;
    }
}
