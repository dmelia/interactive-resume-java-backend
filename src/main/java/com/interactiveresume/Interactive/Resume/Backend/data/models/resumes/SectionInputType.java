package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "resume_section_input_types")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// This class defines the fields of the section types
public class SectionInputType {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

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

    @OneToMany(mappedBy = "inputType", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ElementValue> values = new ArrayList<>();
}
