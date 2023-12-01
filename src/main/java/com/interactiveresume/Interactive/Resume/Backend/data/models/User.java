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
@Getter
@Setter
public class User implements DataTransferObject<UserDTO> {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    @ModelField(name = "id")
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "username")
    private String username;


    @JsonIgnore
    @Column(name = "password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    @ModelField(name = "password")
    private String password;


    @Column(name = "firstname", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "firstname")
    private String firstname;


    @Column(name = "lastname", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "lastname")
    private String lastname;


    @Column(name = "email", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    @ModelField(name = "email")
    private String email;


    @JsonIgnore
    @Column(name = "active")
    @NotNull
    @ModelField(name = "active")
    private boolean active;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    @ModelField(name = "roles")
    private Set<Role> roles = new HashSet<>();


    @OneToMany(mappedBy = "user")
    @ModelField(name = "resumes")
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
