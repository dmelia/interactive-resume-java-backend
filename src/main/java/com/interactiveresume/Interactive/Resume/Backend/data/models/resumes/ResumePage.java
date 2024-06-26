package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@Table(name = "resume_pages")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResumePage {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_pages_id_seq")
    @SequenceGenerator(name = "resume_pages_id_seq", sequenceName = "resume_pages_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "index")
    private Integer index;

    @Version
    @Column(name = "version")
    private int version;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "resume_id", referencedColumnName = "id", nullable = false)
    // Link to the actual resume
    private Resume resume;

    @OneToMany(mappedBy = "page", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Section> sections;
}
