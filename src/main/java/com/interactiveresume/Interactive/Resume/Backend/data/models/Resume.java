package com.interactiveresume.Interactive.Resume.Backend.data.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "resumes")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Resume {


    @Id
    @GeneratedValue
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "name")
    private String name;

    @Getter
    @Setter
    @ManyToOne(fetch = FetchType.EAGER)
    @Column(name = "user_id", nullable = false)
    private User user;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;
}
