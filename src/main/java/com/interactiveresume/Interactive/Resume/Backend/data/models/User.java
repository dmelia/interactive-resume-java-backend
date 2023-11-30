package com.interactiveresume.Interactive.Resume.Backend.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.UserDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.interfaces.DataTransferObject;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User implements DataTransferObject<UserDTO> {

    @Getter
    @Setter
    @JsonIgnore
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @ModelField(name = "id")
    private Long id;

    @Getter
    @Setter
    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "username")
    private String username;

    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    @ModelField(name = "password")
    private String password;

    @Getter
    @Setter
    @Column(name = "firstname", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "firstname")
    private String firstname;

    @Getter
    @Setter
    @Column(name = "lastname", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "lastname")
    private String lastname;

    @Getter
    @Setter
    @Column(name = "email", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "email")
    private String email;

    @Getter
    @Setter
    @JsonIgnore
    @Column(name = "active")
    @NotNull
    @ModelField(name = "active")
    private boolean active;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    @Getter
    @Setter
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    @ModelField(name = "active")
    private Set<Role> roles = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "user")
    @ModelField(name = "active")
    private List<Resume> resumes = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", activated=" + active +
                '}';
    }

    @Override
    public Class<UserDTO> getModelClass() {
        return UserDTO.class;
    }
}
