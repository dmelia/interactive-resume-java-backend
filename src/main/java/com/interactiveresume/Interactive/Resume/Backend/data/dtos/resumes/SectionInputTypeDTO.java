package com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionInputTypeDTO {

    @Nullable
    private Long id;

    @NotNull
    private Long sectionTypeId;

    @Nullable
    private String title;

    @Nullable
    private String type;

    @Nullable
    private int position;
}
