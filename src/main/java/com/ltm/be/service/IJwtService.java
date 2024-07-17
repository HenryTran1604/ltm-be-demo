package com.ltm.be.service;

public interface IJwtService {
    String generateAccessToken(String username);
    String generateRefreshToken(String username);
    String extractUsername(String token);
    boolean isValid(String token);
}
