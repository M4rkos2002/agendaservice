package com.besysoft.agenda.presentation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpForm {

        private String email;
        private String password;
        private String name;


        public SignUpForm() {
        }


}
