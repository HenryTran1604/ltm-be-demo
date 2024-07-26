package com.ltm.be.converter;

import com.ltm.be.dto.PracticeUserExerciseDto;
import com.ltm.be.entity.PracticeUserExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PracticeUserExerciseConverter {
    private final ExerciseConverter exerciseConverter;
    public PracticeUserExerciseDto toDto(PracticeUserExerciseEntity entity) {
        PracticeUserExerciseDto dto = new PracticeUserExerciseDto();
        dto.setId(entity.getId());
        dto.setExercise(exerciseConverter.toDto(entity.getExercise()));
        dto.setUserId(entity.getUser().getId());
        dto.setAc(entity.isAc());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setAlias(entity.getAlias().getCode());
        dto.setAttemptCount(entity.getPracticeSubmissions().size());
        return dto;
    }
}
