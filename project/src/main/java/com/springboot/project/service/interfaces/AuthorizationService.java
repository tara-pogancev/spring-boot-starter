package com.springboot.project.service.interfaces;

public interface AuthorizationService {

    String getUsername();

    boolean isEmailInJWT(String email);

    boolean isEmailInJWT(Long userId);

    void isUserAllowedToAccessResource(String userEmail);

    void isUserAllowedToAccessResource(Long userId);

}
