package com.ltm.be.converter;

import com.ltm.be.dto.ExamSubmissionDto;
import com.ltm.be.entity.ExamSubmissionEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamSubmissionConverter extends AbstractBaseConverter<ExamSubmissionDto, ExamSubmissionEntity>{
    private final ExamUserExerciseConverter examUserExerciseConverter;
    public ExamSubmissionDto toDto(ExamSubmissionEntity entity) {
        return ExamSubmissionDto.builder()
                .id(entity.getId())
                .examUserExercise(examUserExerciseConverter.toDto(entity.getExamUserExercise()))
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
