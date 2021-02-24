package com.app.ecommerce.services;

import com.app.ecommerce.Security.JwtProvider;
import com.app.ecommerce.dto.AuthenticationResponse;
import com.app.ecommerce.dto.LoginRequest;
import com.app.ecommerce.dto.RegisterRequest;
import com.app.ecommerce.exceptions.MonaimException;
import com.app.ecommerce.models.NotificationEmail;
import com.app.ecommerce.models.User;
import com.app.ecommerce.models.VerificationToken;
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

    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user = new User();
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRegisteredAt(Instant.now());
        user.setEnabled(false);
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
        return new AuthenticationResponse(token, loginRequest.getEmail());
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
        verificationToken.orElseThrow(() -> new MonaimException("invalid token"));
        fetchUserAndEnable(verificationToken.get());
        verificationTokenRepository.delete(verificationToken.get());
    }

    @Transactional
    public void fetchUserAndEnable(VerificationToken verificationToken){
        User user = verificationToken.getUser();
        user.setEnabled(true);
        userRepository.save(user);
    }
}
