package com.springboot.project.converter;

import com.springboot.project.dto.ApplicationUserDTO;
import com.springboot.project.model.ApplicationUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ApplicationUserToDTO implements Converter<ApplicationUser, ApplicationUserDTO> {

    @Override
    public ApplicationUserDTO convert(ApplicationUser source) {
        ApplicationUserDTO dto = new ApplicationUserDTO();
        dto.setEmail(source.getEmail());
        dto.setFirstName(source.getFirstName());
        dto.setLastName(source.getLastName());
        dto.setId(source.getId());
        return dto;
    }

}
