package com.interactiveresume.Interactive.Resume.Backend.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@Entity
@Table(name = "resume_misc_block_elements")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CVMiscBlockElement {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "placement")
    private int placement;

    @Column(name = "title")
    private String title;

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
    private CVMiscBlock block;
}
