package com.example.welcome.security;


import com.example.welcome.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;
import java.security.Key;
import java.security.SignatureException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private final String secretKey = "secretHaiVaiMatDekhGalatBaatHotiHai";

    private Key getSecretKey() {
        byte[] keyBytes = secretKey.getBytes(Charset.defaultCharset());
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public Claims extractALlClaims(String token) throws ExpiredJwtException {
        return Jwts
                .parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) throws SignatureException {
        final Claims claims = extractALlClaims(token);
        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token) throws SignatureException {
        return extractClaim(token, Claims::getSubject);
    }


    public String generateToken(User user) {
        return generateToken(user, new HashMap<>());
    }
    public String generateToken(User user, Map<String, Object> extraClaims) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey(), SignatureAlgorithm.HS256)
                .compact();
    }

}
