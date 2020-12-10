package com.app.ecommerce.repositories;

import com.app.ecommerce.models.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Long> {
    public Optional<VerificationToken> findByToken(String token);
}
