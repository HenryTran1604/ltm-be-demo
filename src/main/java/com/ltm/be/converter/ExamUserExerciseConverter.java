package com.ltm.be.converter;

import com.ltm.be.dto.ExamUserExerciseDto;
import com.ltm.be.entity.ExamUserExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamUserExerciseConverter extends AbstractBaseConverter<ExamUserExerciseDto, ExamUserExerciseEntity>{
    private final ExamExerciseConverter examExerciseConverter;
    public ExamUserExerciseDto toDto(ExamUserExerciseEntity entity) {
        return ExamUserExerciseDto.builder()
                .id(entity.getId())
                .userExamId(entity.getExamUser().getId())
                .examExercise(examExerciseConverter.toDto(entity.getExamExercise()))
                .ac(entity.isAc())
                .alias(entity.getAlias().getCode())
                .createdAt(entity.getCreatedAt())
                .attemptCount(entity.getExamSubmissions().size())
                .build();
    }
}
