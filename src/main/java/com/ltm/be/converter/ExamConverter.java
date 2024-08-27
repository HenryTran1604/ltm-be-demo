package com.ltm.be.converter;

import com.ltm.be.dto.ExamDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.payload.request.ExamRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExamConverter {
    private final ExamTypeConverter examTypeConverter;
    public ExamDto toDto(ExamEntity entity) {
        return ExamDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .examType(examTypeConverter.toDto(entity.getExamType()))
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
