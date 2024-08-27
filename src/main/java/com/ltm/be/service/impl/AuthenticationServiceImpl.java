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
    private final IJwtService jwtService;
    private final IUserService userService;
    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse register(RegistrationRequest request) {
        UserDto user = userService.create(request);
        String accessToken = jwtService.generateAccessToken(request.getUserName());
        String refreshToken = jwtService.generateRefreshToken(request.getUserName());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(user)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUserName().toLowerCase(),
                request.getPassword()
        ));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String role = userDetails.getRoles().stream().toList().get(0).toString();
        if (role.equalsIgnoreCase("ROLE_USER") && !userService.existsByUsernameAndIp(request.getUserName(), request.getIpAddress())) {
            throw new DataConflictException("Student has register with different IP!");
        }
        String accessToken = jwtService.generateAccessToken(userDetails.getUsername());
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername());
        UserDto user = UserDto.builder()
                .id(userDetails.getId())
                .userName(userDetails.getUsername())
                .ipAddress(userDetails.getIpAddress())
                .role(role)
                .build();
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .user(user)
                .build();
    }
}
