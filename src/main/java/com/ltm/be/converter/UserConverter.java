package com.ltm.be.converter;

import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter extends AbstractBaseConverter<UserDto, UserEntity>{
    public UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .ip(entity.getIp())
                .createdAt(entity.getCreatedAt())
                .role(entity.getRole().getName())
                .build();
    }
}
