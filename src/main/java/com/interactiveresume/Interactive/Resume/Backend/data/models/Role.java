package com.interactiveresume.Interactive.Resume.Backend.data.models;

import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.RoleDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.interfaces.DataTransferObject;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Table(name = "roles")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Role implements DataTransferObject<RoleDTO> {

    @Id
    @Column(name = "name", length = 50, unique = true)
    @NotNull
    @ModelField(name = "name")
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Authority{" +
                "name=" + name +
                '}';
    }

    @Override
    public Class<RoleDTO> getModelClass() {
        return RoleDTO.class;
    }
}