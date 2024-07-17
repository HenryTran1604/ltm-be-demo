package com.ltm.be.service.impl;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.LoginRequest;
import com.ltm.be.payload.request.RegisterRequest;
import com.ltm.be.payload.response.LoginResponse;
import com.ltm.be.repository.RoleRepository;
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
    public LoginResponse register(RegisterRequest request) {
        UserDto userResponse = userService.addUser(request);
        String accessToken = jwtService.generateAccessToken(request.getStudentCode());
        String refreshToken = jwtService.generateRefreshToken(request.getStudentCode());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userDto(userResponse)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getStudentCode(),
                request.getPassword()
        ));
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        String accessToken = jwtService.generateAccessToken(userDetails.getUsername());
        String refreshToken = jwtService.generateRefreshToken(userDetails.getUsername());
        UserDto userDto = new UserDto();
        userDto.setId(userDetails.getId());
        userDto.setStudentCode(userDetails.getStudentCode());
        userDto.setCreatedAt(userDetails.getCreatedAt());
        userDto.setIp(userDetails.getIp());
        userDto.setRole(userDetails.getRoles().stream().toList().get(0).toString());
        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .userDto(userDto)
                .build();
    }
}
