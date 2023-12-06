package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthRequestDTO implements Serializable {

    private String username;

    private String password;
}
