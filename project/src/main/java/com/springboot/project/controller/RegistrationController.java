package com.springboot.project.controller;

import com.springboot.project.dto.ApplicationUserDTO;
import com.springboot.project.model.ApplicationUser;
import com.springboot.project.service.interfaces.ProjectApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final ProjectApplicationUserService userService;

    @PostMapping
    public ResponseEntity<?> registerNewUser(@RequestBody ApplicationUserDTO newUser) {
        ApplicationUser createdUser = userService.registerNewUser(newUser);
        if (createdUser != null) return new ResponseEntity(createdUser.getFullName(), HttpStatus.OK);
        else return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

}
