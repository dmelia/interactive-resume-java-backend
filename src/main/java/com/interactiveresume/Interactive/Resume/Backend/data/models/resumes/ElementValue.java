package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionElement;
import com.interactiveresume.Interactive.Resume.Backend.data.models.resumes.SectionInputType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "resume_element_values")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ElementValue {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "value")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "section_element_id", referencedColumnName = "id")
    private SectionElement sectionElement;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "input_type_id", referencedColumnName = "id", nullable = false)
    private SectionInputType inputType;
}
