package com.ltm.be.converter;

import com.ltm.be.dto.ExerciseContestDto;
import com.ltm.be.entity.ExerciseContestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExerciseContestConverter {
    private final ExerciseConverter exerciseConverter;
    public ExerciseContestDto toDto(ExerciseContestEntity entity) {
        ExerciseContestDto dto = new ExerciseContestDto();
        dto.setId(entity.getId());
        dto.setExerciseDto(exerciseConverter.toDto(entity.getExercise()));
        dto.setContestId(entity.getContest().getId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
