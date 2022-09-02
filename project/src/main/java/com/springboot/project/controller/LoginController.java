package com.springboot.project.controller;

import com.springboot.project.model.ApplicationUser;
import com.springboot.project.security.JwtUtil;
import com.springboot.project.security.model.AuthenticationRequest;
import com.springboot.project.security.model.AuthenticationResponse;
import com.springboot.project.service.interfaces.ProjectApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class LoginController {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;
    private final ProjectApplicationUserService userService;
    private final AuthenticationManager authenticationManager;

    @PostMapping
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            UsernamePasswordAuthenticationToken token =
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword());
            authenticationManager.authenticate(token);
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect email or password.", e);
        }

        ApplicationUser user = userService.findByEmail(authenticationRequest.getEmail());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt, user.getFullName(), user.getEmail(), user.getId()));
    }

    @PostMapping("/email-exists")
    public ResponseEntity<?> emailExists(@RequestBody AuthenticationRequest authenticationRequest) {
        ApplicationUser emailExists = userService.findByEmail(authenticationRequest.getEmail());
        return ResponseEntity.ok(emailExists != null);
    }

}
