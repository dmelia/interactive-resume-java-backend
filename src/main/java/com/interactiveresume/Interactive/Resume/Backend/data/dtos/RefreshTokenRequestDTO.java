package com.interactiveresume.Interactive.Resume.Backend.data.dtos;

import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;
import com.interactiveresume.Interactive.Resume.Backend.data.interfaces.DataTransferObject;
import com.interactiveresume.Interactive.Resume.Backend.data.models.RefreshToken;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class RefreshTokenRequestDTO implements Serializable, DataTransferObject<RefreshToken> {

    @ModelField(name = "token")
    private String token;

    @Override
    public Class<RefreshToken> getModelClass() {
        return RefreshToken.class;
    }
}