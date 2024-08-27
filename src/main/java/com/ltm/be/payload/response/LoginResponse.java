package com.ltm.be.payload.response;

import com.ltm.be.dto.UserDto;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private UUID userId;
}
