package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.auth.RoleDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The Role and RoleDTO mapper
 */
@Component
public class RoleDTOMapper implements DTOMapper<RoleDTO, Role> {

    /**
     * {@inheritDoc}
     */
    @Override
    public RoleDTO mapModel(Role role) {
        return RoleDTO.builder()
                .name(role.getName())
                .build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Role mapDTO(RoleDTO roleDTO) {
        return Role.builder()
                .name(roleDTO.getName())
                .build();
    }
}
