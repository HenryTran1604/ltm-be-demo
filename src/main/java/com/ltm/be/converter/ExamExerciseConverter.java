package com.ltm.be.converter;

import com.ltm.be.dto.ExamDetailDto;
import com.ltm.be.entity.ExamDetailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamExerciseConverter extends AbstractBaseConverter<ExamDetailDto, ExamDetailEntity>{
    private final ExerciseConverter exerciseConverter;
    public ExamDetailDto toDto(ExamDetailEntity entity) {
        return ExamDetailDto.builder()
                .id(entity.getId())
                .exercise(exerciseConverter.toDto(entity.getQuestion()))
                .examTopicId(entity.getExamTopic().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
