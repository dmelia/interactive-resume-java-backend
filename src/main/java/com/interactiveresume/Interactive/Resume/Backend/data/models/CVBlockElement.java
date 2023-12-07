package com.interactiveresume.Interactive.Resume.Backend.data.models;

import jakarta.persistence.*;
import lombok.*;

import javax.validation.constraints.NotNull;
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

    @Column(name = "icon")
    private String icon;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "block_id", referencedColumnName = "id", nullable = false)
    private CVBlock block;
}
