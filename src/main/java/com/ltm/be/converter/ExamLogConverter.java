package com.ltm.be.converter;

import com.ltm.be.dto.ExamRunDto;
import com.ltm.be.entity.ExamRunEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamLogConverter extends AbstractBaseConverter<ExamRunDto, ExamRunEntity>{
    @Override
    public ExamRunDto toDto(ExamRunEntity entity) {
        return ExamRunDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .examId(entity.getExam().getId())
                .examUserId(entity.getExamUser().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
