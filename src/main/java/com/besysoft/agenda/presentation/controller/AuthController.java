package com.besysoft.agenda.presentation.controller;

import com.besysoft.agenda.business.impl.AuthService;
import com.besysoft.agenda.presentation.dto.AuthenticationRequest;
import com.besysoft.agenda.presentation.dto.AuthenticationResponse;
import com.besysoft.agenda.presentation.dto.SignUpForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<AuthenticationResponse> signUpUser(@RequestBody SignUpForm user){
        return ResponseEntity.ok(authService.signUp(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> logUser(@RequestBody AuthenticationRequest authenticationRequest) {
        AuthenticationResponse a = authService.authenticate(authenticationRequest);

        if (a != null){
            return ResponseEntity.ok(a);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }


}
