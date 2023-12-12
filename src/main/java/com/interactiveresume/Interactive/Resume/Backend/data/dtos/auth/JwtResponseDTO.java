package com.interactiveresume.Interactive.Resume.Backend.data.dtos.auth;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtResponseDTO implements Serializable {

    private String accessToken;
}
