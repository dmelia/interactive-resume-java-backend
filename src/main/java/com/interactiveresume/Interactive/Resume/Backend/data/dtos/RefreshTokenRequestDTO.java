package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RefreshTokenRequestDTO implements Serializable {

    private String token;
}