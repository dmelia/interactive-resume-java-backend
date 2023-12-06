package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVExperienceDTO {

    @Nullable
    private Long id;

    @Nullable
    private Integer placement;

    @Nullable
    private String title;

    @Nullable
    private String company;

    @Nullable
    private String description;

    @Nullable
    private Date startDate;

    @Nullable
    private Date endDate;

    @Nullable
    private Boolean currentPosition;

    @Nullable
    private String footnotes;

    @Nullable
    private String notes;

    @Nullable
    private Long resumeId;
}
