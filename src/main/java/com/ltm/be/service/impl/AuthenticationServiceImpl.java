package com.ltm.be.service.impl;

import com.ltm.be.dto.UserDto;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.payload.request.LoginRequest;
import com.ltm.be.payload.request.RegistrationRequest;
import com.ltm.be.payload.response.LoginResponse;
import com.ltm.be.security.CustomUserDetails;
import com.ltm.be.service.IAuthenticationService;
import com.ltm.be.service.IJwtService;
import com.ltm.be.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements IAuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final IUserService userService;
    private final IJwtService jwtService;

    @Override
    public LoginResponse register(RegistrationRequest request) {
        UserDto userResponse = userService.addUser(request);
        String accessToken = jwtService.generateAccessToken(request.getUsername());
        String refreshToken = jwtService.generateRefreshToken(request.getUsername());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(userResponse)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        ));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String role = userDetails.getRoles().stream().toList().get(0).toString();
        if (role.equalsIgnoreCase("ROLE_USER") && !userService.existsByUsernameAndIp(request.getUsername(), request.getIp())) {
            throw new DataConflictException("Student has register with different IP!");
        }
        String accessToken = jwtService.generateAccessToken(userDetails.getUsername());
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername());
        UserDto user = new UserDto();
        user.setId(userDetails.getId());
        user.setUsername(userDetails.getUsername());
        user.setCreatedAt(userDetails.getCreatedAt());
        user.setIp(userDetails.getIp());
        user.setRole(role);
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(user)
                .build();
    }
}
