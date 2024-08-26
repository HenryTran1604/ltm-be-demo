package com.ltm.be.converter;

import com.ltm.be.dto.ExamDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.payload.request.ExamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamConverter extends AbstractBaseConverter<ExamDto, ExamEntity>{
    private final ExamTypeConverter examTypeConverter;
    @Override
    public ExamDto toDto(ExamEntity entity) {
        return ExamDto.builder()
                .id(entity.getId())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .title(entity.getTitle())
                .examType(examTypeConverter.toDto(entity.getExamType()))
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
