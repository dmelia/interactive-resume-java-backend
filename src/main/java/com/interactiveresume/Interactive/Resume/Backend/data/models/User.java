package com.interactiveresume.Interactive.Resume.Backend.data.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.interactiveresume.Interactive.Resume.Backend.utils.EncryptionService;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.lang.Nullable;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Entity
@Table(name = "users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "username", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;


    @JsonIgnore
    @Column(name = "password", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    private String password;

    /**
     * Used to automatically encrypt the password when saving to the database
     */
    @PrePersist
    public void encryptPassword() {
        if (this.password != null) {
            PasswordEncoder passwordEncoder = EncryptionService.passwordEncoder();
            this.password = passwordEncoder.encode(this.password);
        }
    }

    @Column(name = "firstname", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;


    @Column(name = "lastname", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;


    @Column(name = "email", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    @Email
    private String email;


    @Column(name = "active")
    @Nullable
    private Boolean active;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;


    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_name", referencedColumnName = "name")})
    @BatchSize(size = 20)
    private Set<Role> roles = new HashSet<>();

    @OneToMany(mappedBy = "user")
    //@ModelCollection(name = "resumes")
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
}
