package com.ltm.be.converter;

import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserConverter {

    public UserEntity toEntity(UserDto dto) {
        UserEntity entity = new UserEntity();
        entity.setId(dto.getId());
        entity.setStudentCode(dto.getStudentCode());
        entity.setIp(dto.getIp());
        entity.setCreatedAt(dto.getCreatedAt());
        return entity;
    }
    public UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setStudentCode(entity.getStudentCode());
        dto.setIp(entity.getIp());
        dto.setCreatedAt(entity.getCreatedAt());
        List<SubmissionEntity> submissions = entity.getSubmissions();

        int score = entity.getSubmissions() == null ? 0 : entity.getSubmissions().stream().mapToInt(SubmissionEntity::getAc).sum();
        dto.setScore(score);
        return dto;
    }
}
