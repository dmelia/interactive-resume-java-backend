package com.interactiveresume.Interactive.Resume.Backend.data.mapping;

import com.interactiveresume.Interactive.Resume.Backend.data.dtos.RoleDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.models.Role;
import org.springframework.stereotype.Component;

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