package com.interactiveresume.Interactive.Resume.Backend.data.mapping;


import com.interactiveresume.Interactive.Resume.Backend.data.dtos.auth.UserDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * The User and UserDTO mapper
 */
@Component
public class UserDTOMapper implements DTOMapper<UserDTO, User>{

    /**
     * {@inheritDoc}
     */
    @Override
    public UserDTO mapModel(User model) {
        return UserDTO.builder()
                .username(model.getUsername())
                .active(model.getActive())
                .email(model.getEmail())
                .firstname(model.getFirstname())
                .id(model.getId())
                .lastname(model.getLastname())
                //.password(model.getPassword()) We never return the password from the model
                .build();
    }
}
