package com.ltm.be.converter;

import com.ltm.be.dto.ExamRankDto;
import com.ltm.be.entity.ExamUserEntity;
import com.ltm.be.entity.ExamUserExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamRankConverter extends AbstractBaseConverter<ExamRankDto, ExamUserEntity> {
    private final ExamUserExerciseConverter examUserExerciseConverter;
    public ExamRankDto toDto(ExamUserEntity entity)  {
        return ExamRankDto.builder()
                .id(entity.getId())
                .username(entity.getUser().getUsername())
                .ip(entity.getUser().getIp())
                .examUserId(entity.getId())
                .examUserExercises(entity.getExamUserExercises().stream().map(examUserExerciseConverter::toDto).toList())
                .score((int) entity.getExamUserExercises().stream()
                        .filter(ExamUserExerciseEntity::isAc)
                        .count())
                .build();
    }
}
