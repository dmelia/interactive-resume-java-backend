package com.interactiveresume.Interactive.Resume.Backend.data.models;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "resume_section_elements")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// This class defines the values of the sections inside a resume
public class SectionElement {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "position")
    private int position;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "section_type_id", referencedColumnName = "id", nullable = false)
    private SectionType sectionType;

    @OneToMany(mappedBy = "sectionElement", cascade = CascadeType.ALL, orphanRemoval = true)
    // This contains the values of the fields
    private List<ElementValue> elements = new ArrayList<>();
}
