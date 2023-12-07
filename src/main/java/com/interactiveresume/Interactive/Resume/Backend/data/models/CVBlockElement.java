package com.interactiveresume.Interactive.Resume.Backend.data.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "resume_block_elements")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CVBlockElement {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "placement")
    private int placement;

    @Column(name = "title")
    private String title;

    @Column(name = "company")
    private String company;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "link")
    private String link;

    @Column(name = "footnotes")
    private String footnotes;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", referencedColumnName = "id", nullable = false)
    private Resume resume;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;
}
