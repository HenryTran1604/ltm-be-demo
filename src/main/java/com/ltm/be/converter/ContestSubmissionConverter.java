package com.ltm.be.converter;

import com.ltm.be.dto.ContestSubmissionDto;
import com.ltm.be.entity.ContestSubmissionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContestSubmissionConverter {
    private final ContestUserExerciseConverter contestUserExerciseConverter;
    public ContestSubmissionDto toDto(ContestSubmissionEntity entity) {
        ContestSubmissionDto dto = new ContestSubmissionDto();
        dto.setId(entity.getId());
        dto.setContestUserExercise(contestUserExerciseConverter.toDto(entity.getContestUserExercise()));
        dto.setAc(entity.isAc());
//        dto.setSrcPath(entity.getSrcPath());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
