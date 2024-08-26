package com.ltm.be.converter;

import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.entity.SubmissionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamSubmissionConverter extends AbstractBaseConverter<SubmissionDto, SubmissionEntity>{
    private final ExamUserExerciseConverter examUserExerciseConverter;
    public SubmissionDto toDto(SubmissionEntity entity) {
        return SubmissionDto.builder()
                .id(entity.getId())
                .examUserExercise(examUserExerciseConverter.toDto(entity.getExamUserExercise()))
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
