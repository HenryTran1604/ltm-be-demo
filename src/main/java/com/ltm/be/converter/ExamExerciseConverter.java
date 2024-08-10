package com.ltm.be.converter;

import com.ltm.be.dto.ExamExerciseDto;
import com.ltm.be.entity.ExamExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamExerciseConverter extends AbstractBaseConverter<ExamExerciseDto, ExamExerciseEntity>{
    private final ExerciseConverter exerciseConverter;
    public ExamExerciseDto toDto(ExamExerciseEntity entity) {
        return ExamExerciseDto.builder()
                .id(entity.getId())
                .exercise(exerciseConverter.toDto(entity.getExercise()))
                .examId(entity.getExam().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
