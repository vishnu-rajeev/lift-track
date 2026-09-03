package com.vishnurajeev.lifttrack.user;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.sql.Date;
import java.time.Instant;
import java.util.Base64;

@Service
public class JwtService {

    private final SecretKey signingKey;

    public JwtService(@Value("${JWT_SECRET}") String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        signingKey = Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(User user) {
        Instant now = Instant.now();
        Instant expiration = now.plusSeconds(60 * 60 * 24);
        return Jwts.builder()
                .subject(user.getId().toString())
                .issuedAt(Date.from(now))
                .expiration(Date.from(expiration))
                .signWith(signingKey)
                .compact();
    }
}
