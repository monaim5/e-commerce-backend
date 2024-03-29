package com.app.ecommerce.services;

import com.app.ecommerce.Security.JwtProvider;
import com.app.ecommerce.models.dtos.AuthenticationResponse;
import com.app.ecommerce.models.dtos.LoginRequest;
import com.app.ecommerce.models.dtos.RefreshTokenRequest;
import com.app.ecommerce.models.dtos.RegisterRequest;
import com.app.ecommerce.exceptions.AuthorizationException;
import com.app.ecommerce.models.entities.User;
import com.app.ecommerce.models.enums.UserStatus;
import com.app.ecommerce.models.structure.NotificationEmail;
import com.app.ecommerce.models.structure.VerificationToken;
import com.app.ecommerce.repositories.UserRepository;
import com.app.ecommerce.repositories.VerificationTokenRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RefreshTokenService refreshTokenService;

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
//        user.setCreationDate(Instant.now());
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail(
                "verification email",
                user.getEmail(),
                "click here to verify your account http://localhost:8080/api/auth/accountVerification/" + token
        ));

    }

    public AuthenticationResponse signin(LoginRequest loginRequest){
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        System.out.println(token);

        return AuthenticationResponse.builder()
                .username(loginRequest.getEmail())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getExpirationTime()))
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .authenticationToken(token)
                .build();
    }

    private String generateVerificationToken(User user){
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new AuthorizationException("invalid token"));
        fetchUserAndEnable(verificationToken.get());
        verificationTokenRepository.delete(verificationToken.get());
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken){
        User user = verificationToken.getUser();
        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        String token = jwtProvider.generateTokenByUsername(refreshTokenRequest.getUsername());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getExpirationTime()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
}
