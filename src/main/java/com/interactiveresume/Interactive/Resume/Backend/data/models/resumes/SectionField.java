package com.interactiveresume.Interactive.Resume.Backend.data.models.resumes;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "resume_section_fields")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SectionField {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resume_section_fields_id_seq")
    @SequenceGenerator(name = "resume_section_fields_id_seq", sequenceName = "resume_section_fields_id_seq", allocationSize = 1)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "value")
    private String value;

    @Column(name = "type")
    private String type;

    @Column(name = "version")
    private int version;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "section_id", referencedColumnName = "id")
    private Section section;
}
