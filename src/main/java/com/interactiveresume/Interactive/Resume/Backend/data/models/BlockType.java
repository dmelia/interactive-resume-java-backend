package com.interactiveresume.Interactive.Resume.Backend.data.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Entity
@Table(name = "block_types")
@Builder
@AllArgsConstructor
@NoArgsConstructor
// This class represents the different possible values of Block types that can be selected in the front end by the user
public class BlockType {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ")
    @SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "name", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String name;

    // If the user is null, this means it is an application-wide default value, for all users and not modifiable
    @Nullable
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;
}
