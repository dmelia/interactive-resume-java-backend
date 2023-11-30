package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class JwtResponseDTO implements Serializable {

    private String accessToken;
    private String token;
}
