package com.ltm.be.service.impl;

import com.ltm.be.service.IJwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements IJwtService {
    @Value("${token.secretKey}")
    private String secretKey;
    @Value("${token.expirationTime.access}")
    private Long expirationAccessToken;

    @Value("${token.expirationTime.refresh}")
    private Long expirationRefreshToken;
    @Override
    public String generateAccessToken(String username) {
        return generateToken(new HashMap<>(), username, expirationAccessToken);
    }

    @Override
    public String generateRefreshToken(String username) {
        return generateToken(new HashMap<>(), username, expirationRefreshToken);
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean isValid(String token) {
        return isValidFormatToken(token) && !isTokenExpired(token);
    }

    private String generateToken(Map<String, Object> claims, String username, Long expirationTime) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
    private boolean isTokenExpired(String token) {
        return extractExpirationTime(token).before(new Date());
    }
    private boolean isValidFormatToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException exception) {
            return false;
        }
    }
    private Date extractExpirationTime(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
