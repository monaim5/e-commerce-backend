package com.app.ecommerce.services;

import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.models.structure.RefreshToken;
import com.app.ecommerce.repositories.RefreshTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken generateRefreshToken() {
        RefreshToken token = new RefreshToken();
        token.setToken(UUID.randomUUID().toString());
//        token.setCreatedAt(Instant.now());
        return refreshTokenRepository.save(token);
    }

    public RefreshToken validateRefreshToken(String refreshToken) {
        return this.refreshTokenRepository.findByToken(refreshToken)
                .orElseThrow(() -> new MonaimException("invalid refresh token"));
    }

    public void deleteRefreshToken(String refreshToken) {
        this.refreshTokenRepository.deleteByToken(refreshToken);
    }
}
