package com.ltm.be.converter;

import com.ltm.be.dto.ExamDto;
import com.ltm.be.dto.ExamLogDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.entity.ExamLogEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamLogConverter extends AbstractBaseConverter<ExamLogDto, ExamLogEntity>{
    public ExamLogDto toDto(ExamLogEntity entity) {
        return ExamLogDto.builder()
                .id(entity.getId())
                .content(entity.getContent())
                .examId(entity.getExam().getId())
                .examUserId(entity.getExamUser().getId())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
