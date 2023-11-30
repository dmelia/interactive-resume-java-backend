package com.interactiveresume.Interactive.Resume.Backend.data.models;

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
public class Role {

    @Getter
    @Setter
    @Id
    @Column(name = "name", length = 50, unique = true)
    @NotNull
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
}