package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;
import com.interactiveresume.Interactive.Resume.Backend.data.interfaces.DataTransferObject;
import com.interactiveresume.Interactive.Resume.Backend.data.models.Role;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RoleDTO implements Serializable, DataTransferObject<Role> {

    @ModelField(name = "name")
    private String name;
    @Override
    public Class<Role> getModelClass() {
        return Role.class;
    }
}
