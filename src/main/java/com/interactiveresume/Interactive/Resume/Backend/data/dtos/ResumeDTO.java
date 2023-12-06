package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResumeDTO {

    @Nullable
    private Long id;

    @Nullable
    private String name;
}
