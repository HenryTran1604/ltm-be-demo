package com.ltm.be.converter;

import com.ltm.be.dto.ContestUserExerciseDto;
import com.ltm.be.entity.ContestUserExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContestUserExerciseConverter {
    private final ContestExerciseConverter contestExerciseConverter;
    public ContestUserExerciseDto toDto(ContestUserExerciseEntity entity) {
        ContestUserExerciseDto dto = new ContestUserExerciseDto();
        dto.setId(entity.getId());
        dto.setUserContestId(entity.getContestUser().getId());
        dto.setContestExercise(contestExerciseConverter.toDto(entity.getContestExercise()));
        dto.setAc(entity.isAc());
        dto.setAlias(entity.getAlias().getCode());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setAttemptCount(entity.getContestSubmissions().size());
        return dto;
    }
}
