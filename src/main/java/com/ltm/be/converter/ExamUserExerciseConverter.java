package com.ltm.be.converter;

import com.ltm.be.dto.ExamUserDetailDto;
import com.ltm.be.entity.ExamUserDetailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamUserExerciseConverter extends AbstractBaseConverter<ExamUserDetailDto, ExamUserDetailEntity>{
    private final ExamExerciseConverter examExerciseConverter;
    public ExamUserDetailDto toDto(ExamUserDetailEntity entity) {
        return ExamUserDetailDto.builder()
                .id(entity.getId())
                .userExamId(entity.getExamUser().getId())
                .examDetail(examExerciseConverter.toDto(entity.getExamDetail()))
                .ac(entity.isAc())
                .alias(entity.getAlias().getCode())
                .createdAt(entity.getCreatedAt())
                .attemptCount(entity.getSubmissions().size())
                .build();
    }
}
