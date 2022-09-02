package com.springboot.project.service.interfaces;

import com.springboot.project.dto.ApplicationUserDTO;
import com.springboot.project.model.ApplicationUser;

public interface ProjectApplicationUserService {

    ApplicationUser findByEmail(String email);

    ApplicationUser registerNewUser(ApplicationUserDTO newUser);

    ApplicationUser getById(Long id);

}
