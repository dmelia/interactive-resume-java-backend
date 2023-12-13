package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.auth.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
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
public class SectionType implements Cloneable {

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
    @Builder.Default
    private boolean generic = false;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    // If null, this is a generic entry
    @Nullable
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // This list defines the types of fields which will be filled in for each element of the section
    // Example for "education" section type, the elements would inputs would be : university name, obtention date, GPA...
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SectionInputType> elements = new ArrayList<>();

    @Override
    public SectionType clone() {
        try {
            SectionType clone = (SectionType) super.clone();
            // Set the ID to null to ensure it gets a new ID when saved
            clone.setId(null);
            // Set generic to false
            clone.generic = false;
            // Create a deep copy of the list of SectionInputType
            clone.setElements(new ArrayList<>());
            for (SectionInputType inputType : this.elements) {
                SectionInputType inputTypeClone = inputType.clone();
                clone.elements.add(inputTypeClone);
            }
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
