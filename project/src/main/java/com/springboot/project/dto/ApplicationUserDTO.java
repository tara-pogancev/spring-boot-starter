package com.springboot.project.dto;

import lombok.Data;

@Data
public class ApplicationUserDTO {
    public Long id;
    public String firstName;
    public String lastName;
    public String password;
    public String email;
}
