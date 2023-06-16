package com.watchworthy.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class SignUpDTO {
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String confirmPassword;
    private List<String> roles;
}
