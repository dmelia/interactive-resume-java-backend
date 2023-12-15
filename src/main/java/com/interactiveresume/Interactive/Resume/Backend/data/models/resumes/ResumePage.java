package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "index")
    private Long index;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", referencedColumnName = "id", nullable = false)
    // Link to the actual resume
    private Resume resume;

    @OneToMany(mappedBy = "pages", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionElement> sections;
}
