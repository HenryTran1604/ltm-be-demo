package com.ltm.be.converter;

import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.entity.UserExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class UserExerciseConverter {
    public UserExerciseDto toDto(UserExerciseEntity entity) {
        UserExerciseDto dto = new UserExerciseDto();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUser().getId());
        dto.setExerciseId(entity.getExercise().getId());
        dto.setAttemptCount(entity.getSubmissions().size());
        dto.setAc(entity.isAc());
        return dto;
    }
}
