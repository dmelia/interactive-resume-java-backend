package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class AuthRequestDTO implements Serializable {

    private String username;
    private String password;
}
