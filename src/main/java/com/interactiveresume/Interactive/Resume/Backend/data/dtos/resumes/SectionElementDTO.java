package com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionElementDTO {

    @Nullable
    private Long id;

    @Nullable
    private Integer position;

    @NotNull
    private Long sectionTypeId;

    @NotNull
    private Long resumePageId;

    @NotNull
    @Builder.Default
    private List<Long> elements = new ArrayList<>();
}
