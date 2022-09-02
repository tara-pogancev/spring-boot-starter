package com.springboot.project.service;

import com.springboot.project.dto.ApplicationUserDTO;
import com.springboot.project.model.ApplicationUser;
import com.springboot.project.repository.ApplicationUserRepository;
import com.springboot.project.security.JwtUtil;
import com.springboot.project.service.interfaces.ProjectApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ApplicationUserServiceImpl implements ProjectApplicationUserService {

    private final JwtUtil jwtUtil;
    private final ApplicationUserRepository userRepository;

    @Override
    public ApplicationUser findByEmail(String email) {
        if (email.isEmpty())
            return null;
        return userRepository.findUserByEmail(email.toLowerCase().replaceAll("\\s+", ""));
    }

    @Override
    public ApplicationUser registerNewUser(ApplicationUserDTO newUser) {
        if ((this.findByEmail(newUser.email) == null)) {
            ApplicationUser user = new ApplicationUser();
            user.setFirstName(newUser.firstName);
            user.setLastName(newUser.lastName);
            user.setEmail(newUser.email.toLowerCase());
            user.setPassword(new BCryptPasswordEncoder().encode(newUser.password));
            ApplicationUser createdUser = userRepository.save(user);
            return createdUser;
        } else return null;
    }

    @Override
    public ApplicationUser getById(Long id) {
        return userRepository.getById(id);
    }


}
