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
public class ResumePageDTO {

    @Nullable
    private Long id;

    @NotNull
    private Long resumeId;

    @Nullable
    private String name;

    @NotNull
    private Integer index;

    @NotNull
    @Builder.Default
    private List<Long> sections = new ArrayList<>();
}
