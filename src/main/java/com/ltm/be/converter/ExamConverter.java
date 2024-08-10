package com.ltm.be.converter;

import com.ltm.be.dto.ExamDto;
import com.ltm.be.entity.ExamEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamConverter extends AbstractBaseConverter<ExamDto, ExamEntity>{
    @Override
    public ExamDto toDto(ExamEntity entity) {
        return ExamDto.builder()
                .id(entity.getId())
                .startTime(entity.getStartTime())
                .endTime(entity.getEndTime())
                .title(entity.getTitle())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
