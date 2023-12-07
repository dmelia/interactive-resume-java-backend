package com.interactiveresume.Interactive.Resume.Backend.data.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "resume_blocks")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CVBlock {

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "summary")
    private String summary;

    @Column(name = "notes")
    private String notes;

    // String that is selectable in the front end from the values of possible Block Types the user has
    @Column(name = "type")
    private String type;

    @Column(name = "icon")
    private String icon;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "resume_id", referencedColumnName = "id", nullable = false)
    private Resume resume;

    @OneToMany(fetch = FetchType.LAZY)
    private List<CVBlockElement> elements = new ArrayList<>();

    // Configuration of the child elements

    @Column(name = "title_enabled")
    private boolean titleEnabled;

    @Column(name = "company_enabled")
    private boolean companyEnabled;

    @Column(name = "description_enabled")
    private boolean descriptionEnabled;

    @Column(name = "start_date_enabled")
    private boolean startDateEnabled;

    @Column(name = "end_date_enabled")
    private boolean endDateEnabled;

    @Column(name = "link_enabled")
    private boolean linkEnabled;

    @Column(name = "footnotes_enabled")
    private boolean footnotesEnables;

    @Column(name = "notes_enabled")
    private boolean notesEnabled;

    @Column(name = "icon_enabled")
    private boolean iconEnabled;
}
