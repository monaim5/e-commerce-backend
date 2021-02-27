package com.app.ecommerce.Security;

import com.app.ecommerce.exceptions.MonaimException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.sql.Date;
import java.time.Instant;

@Service
public class JwtProvider {

    private KeyStore keyStore;
    @Value("${jwt.expiration.time}")
    private Long expirationTime;

    public Long getExpirationTime(){
        return expirationTime;
    }

    @PostConstruct
    public void init(){
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/monaim_key.jks");
            keyStore.load(resourceAsStream, "user4415".toCharArray());
        } catch (Exception e) {
            throw new MonaimException("exception occurred while loading keystore");
        }
    }

    public String generateToken(Authentication authentication){
        User principal = (User) authentication.getPrincipal();
        return generateTokenByUsername(principal.getUsername());
    }

    public String generateTokenByUsername(String username) {
        return Jwts.builder()
                .signWith(getPrivateKey())
                .setExpiration(Date.from(Instant.now().plusMillis(expirationTime)))
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .compact();
    }

    private PrivateKey getPrivateKey(){
        try {
            return (PrivateKey) keyStore.getKey("monaim", "user4415".toCharArray());
        } catch (Exception e){
            throw new MonaimException("exception occurred while retrieving private key");
        }
    }

    public boolean validateToken(String jwt) {
        Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("monaim").getPublicKey();
        } catch (KeyStoreException e) {
            throw new MonaimException("failed to get the public key");
        }
    }

    public String getUserFromJwt(String token){
        Claims claims = Jwts.parser()
                .setSigningKey(getPublicKey())
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }
}
