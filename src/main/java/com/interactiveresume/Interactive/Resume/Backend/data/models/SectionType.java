package com.interactiveresume.Interactive.Resume.Backend.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "resume_section_types")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// This class defines the types of sections that the user can configure in the resume
// An example would be the "education" section
public class SectionType {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "notes")
    private String notes;

    @Column(name = "icon")
    private String icon;

    // Used if the Section type is generic (a default section type, eg : education)
    // The generic values are copied and linked to a resume when a new one is created, the user is then free to modify them
    @Column(name = "generic")
    private boolean generic;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    // If null, this is a generic entry
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", referencedColumnName = "id")
    private Resume resume;

    // This list defines the types of fields which will be filled in for each element of the section
    // Example for "education" section type, the elements would inputs would be : university name, obtention date, GPA...
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionInputType> elements = new ArrayList<>();
}
