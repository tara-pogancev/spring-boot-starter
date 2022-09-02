package com.springboot.project.security;

import com.springboot.project.model.ApplicationUser;
import com.springboot.project.service.interfaces.ProjectApplicationUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ProjectApplicationUserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        ApplicationUser user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException(email);
        }
        return new User(email, user.getPassword(), new ArrayList<GrantedAuthority>() {
        });
    }

}
