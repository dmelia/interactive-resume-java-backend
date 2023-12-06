package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO implements Serializable {

    @Nullable
    private Long id;

    private String username;

    @Nullable
    private String password;

    @Nullable
    private Boolean active;

    @Nullable
    private String firstname;

    @Nullable
    private String lastname;

    @Nullable
    private String email;
}
