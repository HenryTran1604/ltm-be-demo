package com.ltm.be.converter;

import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.entity.SubmissionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubmissionConverter {
    private final UserExerciseContestConverter userExerciseContestConverter;
    public SubmissionDto toDto(SubmissionEntity entity) {
        SubmissionDto dto = new SubmissionDto();
        dto.setId(entity.getId());
        dto.setUserExerciseContestDto(userExerciseContestConverter.toDto(entity.getUserExerciseContest()));
        dto.setAc(entity.isAc());
        dto.setSrcPath(entity.getSrcPath());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
