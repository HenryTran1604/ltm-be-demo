package com.ltm.be.converter;

import com.ltm.be.dto.ExamRankDto;
import com.ltm.be.entity.ExamUserEntity;
import com.ltm.be.entity.ExamUserDetailEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamRankConverter extends AbstractBaseConverter<ExamRankDto, ExamUserEntity> {
    private final ExamUserExerciseConverter examUserExerciseConverter;
    public ExamRankDto toDto(ExamUserEntity entity)  {
        return ExamRankDto.builder()
                .id(entity.getId())
                .username(entity.getUser().getUserName())
                .ip(entity.getUser().getIpAddress())
                .examUserId(entity.getId())
                .examUserExercises(entity.getExamUserDetails().stream().map(examUserExerciseConverter::toDto).toList())
                .score((int) entity.getExamUserDetails().stream()
                        .filter(ExamUserDetailEntity::isAc)
                        .count())
                .build();
    }
}
