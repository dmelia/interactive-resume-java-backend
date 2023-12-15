package com.interactiveresume.Interactive.Resume.Backend.data.dtos.resumes;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SectionTypeDTO {

    @Nullable
    private Long id;

    @Nullable
    private String title;

    @Nullable
    private String description;

    @Nullable
    private String notes;

    @Nullable
    private String icon;

    @Builder.Default
    private List<Long> elements = new ArrayList<>();
}
