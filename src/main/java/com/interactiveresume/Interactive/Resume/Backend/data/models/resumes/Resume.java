package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "resumes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resume {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "icon")
    private String icon;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    @OneToMany(mappedBy = "resume", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionElement> sections;
}
