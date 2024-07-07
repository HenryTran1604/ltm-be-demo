package com.ltm.be.converter;

import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setIp(dto.getIp());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setIp(entity.getIp());
        dto.setCreatedAt(entity.getCreatedAt());

        // score
//        dto.setScore();

        return dto;
    }
}
