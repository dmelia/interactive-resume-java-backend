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
public class ResumeDTO {

    @Nullable
    private Long id;

    @Nullable
    private String name;

    @Nullable
    private String icon;

    @Builder.Default
    private List<Long> pages = new ArrayList<>();
 }
