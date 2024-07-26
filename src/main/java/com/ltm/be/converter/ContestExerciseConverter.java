package com.ltm.be.converter;

import com.ltm.be.dto.ContestExerciseDto;
import com.ltm.be.entity.ContestExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContestExerciseConverter {
    private final ExerciseConverter exerciseConverter;
    public ContestExerciseDto toDto(ContestExerciseEntity entity) {
        ContestExerciseDto dto = new ContestExerciseDto();
        dto.setId(entity.getId());
        dto.setExercise(exerciseConverter.toDto(entity.getExercise()));
        dto.setContestId(entity.getContest().getId());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
