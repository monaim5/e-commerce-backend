package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.AuthenticationResponse;
import com.app.ecommerce.models.dtos.LoginRequest;
import com.app.ecommerce.models.dtos.RefreshTokenRequest;
import com.app.ecommerce.models.dtos.RegisterRequest;
import com.app.ecommerce.exceptions.AuthorizationException;
import com.app.ecommerce.services.AuthService;
import com.app.ecommerce.services.RefreshTokenService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final RefreshTokenService refreshTokenService;

    @CrossOrigin("http://localhost:4200")
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest){
        authService.signup(registerRequest);
        return new ResponseEntity<>("signup done", HttpStatus.OK);
    }

    @CrossOrigin("*")
    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) throws AuthorizationException {
        authService.verifyAccount(token);
        return new ResponseEntity<>("account activated successfully", HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationResponse> signin(@RequestBody LoginRequest loginRequest) throws AuthorizationException {
        return ResponseEntity.status(HttpStatus.OK).body(authService.signin(loginRequest));
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<AuthenticationResponse> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.refreshToken(refreshTokenRequest));
    }

    @PostMapping("/logout")
    public void logout(@RequestBody RefreshTokenRequest refreshToken) {
        refreshTokenService.deleteRefreshToken(refreshToken.getRefreshToken());
    }
}
