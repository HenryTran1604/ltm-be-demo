package com.ltm.be.converter;

import com.ltm.be.dto.ScoreBoardDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.entity.UserExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoreBoardConverter {
    private final UserExerciseConverter userExerciseConverter;
    public ScoreBoardDto toDto(UserEntity entity) {
        ScoreBoardDto dto = new ScoreBoardDto();
        dto.setId(entity.getId());
        dto.setStudentCode(entity.getStudentCode());
        dto.setIp(entity.getIp());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setScore(entity.getUserExercises() == null ? 0 : (int) entity.getUserExercises().stream()
                .filter(UserExerciseEntity::isAc)
                .count());
        dto.setUserExercises(entity.getUserExercises().stream().map(userExerciseConverter::toDto).toList());
        return dto;
    }
}
