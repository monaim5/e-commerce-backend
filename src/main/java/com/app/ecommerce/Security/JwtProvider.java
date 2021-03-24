package com.app.ecommerce.Security;

import com.app.ecommerce.exceptions.AuthorizationException;
import com.app.ecommerce.exceptions.ExpiredSessionException;
import com.app.ecommerce.exceptions.InternalException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
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
            throw new InternalException("exception occurred while loading keystore");
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
            throw new InternalException("exception occurred while retrieving private key");
        }
    }

    public boolean validateToken(String jwt) {
        try {
            log.info("i am here one");
            Jwts.parser().setSigningKey(getPublicKey()).parseClaimsJws(jwt);
            log.info("i am here two");
        } catch (Exception e) {
            log.info("i am here three");
            throw new AuthorizationException(e.getMessage());
        }
        return true;
    }

    private PublicKey getPublicKey() {
        try {
            return keyStore.getCertificate("monaim").getPublicKey();
        } catch (KeyStoreException e) {
            throw new InternalException("failed to get the public key");
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
