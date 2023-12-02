package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelCollection;
import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;
import com.interactiveresume.Interactive.Resume.Backend.data.interfaces.DataTransferObject;
import com.interactiveresume.Interactive.Resume.Backend.data.models.User;
import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserDTO implements Serializable, DataTransferObject<User> {

    @Nullable
    @ModelField(name = "id")
    private Long id;

    @ModelField(name = "username")
    private String username;

    @Nullable
    @JsonIgnore
    @ModelField(name = "password")
    private String password;

    @ModelCollection(name = "password")
    private Set<RoleDTO> roles;

    @Nullable
    @ModelField(name = "active")
    private boolean activated;

    @Override
    public Class getModelClass() {
        return User.class;
    }
}
