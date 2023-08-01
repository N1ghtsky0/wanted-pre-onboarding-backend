package com.example.demo.global.config.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class TokenProvider {

    private final Key SECRET_KEY;
    private final long ACCESS_TOKEN_EXPIRE_TIME;
    private final String ISSUER;
    private final String AUDIENCE;

    public TokenProvider(
            @Value("${TOKEN.SECRET_KEY}") String secretKey,
            @Value("${TOKEN.ACCESS_TOKEN_EXPIRE_TIME}") long accessTokenExpireTime,
            @Value("${TOKEN.ISSUER}") String issuer,
            @Value("${TOKEN.AUDIENCE}") String audience
    ) {
        this.SECRET_KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
        this.ACCESS_TOKEN_EXPIRE_TIME = accessTokenExpireTime;
        this.ISSUER = issuer;
        this.AUDIENCE = audience;
    }

    public String createToken(String userSpecification) {
        return Jwts.builder()
                .setSubject(userSpecification)
                .setIssuer(ISSUER)
                .setAudience(AUDIENCE)
                .setIssuedAt(Timestamp.valueOf(LocalDateTime.now()))
                .setExpiration(Date.from(Instant.now().plus(ACCESS_TOKEN_EXPIRE_TIME, ChronoUnit.HOURS)))
                .signWith(SECRET_KEY)
                .compact();
    }

    public String validTokenAndGetSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

}
