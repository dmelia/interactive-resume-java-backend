package com.interactiveresume.Interactive.Resume.Backend.data.models;

import com.interactiveresume.Interactive.Resume.Backend.data.annotations.ModelField;
import com.interactiveresume.Interactive.Resume.Backend.data.dtos.RefreshTokenRequestDTO;
import com.interactiveresume.Interactive.Resume.Backend.data.interfaces.DataTransferObject;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "refresh_tokens")
@Getter
@Setter
public class RefreshToken implements DataTransferObject<RefreshTokenRequestDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "token")
    @ModelField(name = "token")
    private String token;

    @Column(name = "expires")
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @PrimaryKeyJoinColumn(name = "user_id")
    private User userInfo;

    @Version
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "version")
    private int version;

    @Override
    public Class<RefreshTokenRequestDTO> getModelClass() {
        return RefreshTokenRequestDTO.class;
    }
}
