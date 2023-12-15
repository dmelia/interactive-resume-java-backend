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
public class ElementValueDTO {

    @Nullable
    private Long id;

    @NotNull
    private Long sectionInputTypeId;

    @NotNull
    private Long sectionElementId;

    @NotNull
    private String value;
}
