package com.app.ecommerce.controllers;

import com.app.ecommerce.models.dtos.*;
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

    @PostMapping("/signup")
    public ResponseEntity<ResponsePayload<String>> signup(@RequestBody RegisterRequest registerRequest){
        ResponsePayload<String> responsePayload = new ResponsePayload<>("User has been created successfully");
        authService.signup(registerRequest);
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @GetMapping("/accountVerification/{token}")
    public ResponseEntity<ResponsePayload<String>> verifyAccount(@PathVariable String token) throws AuthorizationException {
        ResponsePayload<String> responsePayload = new ResponsePayload<>("Account has been actived successfully");
        authService.verifyAccount(token);
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @PostMapping("/signin")
    public ResponseEntity<ResponsePayload<AuthenticationResponse>> signin(@RequestBody LoginRequest loginRequest) throws AuthorizationException {
        ResponsePayload<AuthenticationResponse> responsePayload = new ResponsePayload<>("The user has been logged successfully", authService.signin(loginRequest));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @PostMapping("/refreshToken")
    public ResponseEntity<ResponsePayload<AuthenticationResponse>> refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        ResponsePayload<AuthenticationResponse> responsePayload = new ResponsePayload<>("The token has been refreshed",this.authService.refreshToken(refreshTokenRequest));
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }

    @PostMapping("/logout")
    public ResponseEntity<ResponsePayload<String>> logout(@RequestBody RefreshTokenRequest refreshToken) {
        refreshTokenService.deleteRefreshToken(refreshToken.getRefreshToken());
        ResponsePayload<String> responsePayload = new ResponsePayload<>("The user has been logout");
        return ResponseEntity.status(HttpStatus.OK).body(responsePayload);
    }
}
