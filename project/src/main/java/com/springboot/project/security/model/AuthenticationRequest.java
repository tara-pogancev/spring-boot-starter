package com.springboot.project.security.model;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String email;
    private String password;
}
