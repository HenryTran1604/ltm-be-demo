package com.ltm.be.converter;

import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.entity.UserExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserExerciseConverter {
    public UserExerciseDto toDto(UserExerciseEntity entity) {
        UserExerciseDto dto = new UserExerciseDto();
        dto.setId(entity.getId());
        dto.setTryCount(entity.getTryCount());
        dto.setAC(entity.isAC());
        return dto;
    }
}
