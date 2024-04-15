package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "resume_sections")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// This class defines the values of the sections inside a resume
public class Section {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_sections_id_seq")
    @SequenceGenerator(name = "resume_sections_id_seq", sequenceName = "resume_sections_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "position")
    private int position;

    @Version
    @Column(name = "version")
    private int version;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "page_id", referencedColumnName = "id", nullable = false)
    // Link to the actual resume
    private ResumePage page;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, orphanRemoval = true)
    // This contains the values of the fields
    private List<SectionField> fields = new ArrayList<>();
}
