package com.ltm.be.converter;

import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.entity.UserExerciseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setStudentCode(dto.getStudentCode());
        entity.setIp(dto.getIp());

        return entity;
    }

    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setStudentCode(entity.getStudentCode());
        dto.setIp(entity.getIp());
        dto.setCreatedAt(entity.getCreatedAt());
//        dto.setScore(entity.getUserExercises() == null ? 0 : (int) entity.getUserExercises().stream()
//                .filter(UserExerciseEntity::isAc)
//                .count());
        return dto;
    }
}
