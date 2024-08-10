package com.ltm.be.converter;

import com.ltm.be.dto.ExamTypeDto;
import com.ltm.be.entity.ExamTypeEntity;
import org.springframework.stereotype.Component;

@Component
public class ExamTypeConverter extends AbstractBaseConverter<ExamTypeDto, ExamTypeEntity> {
    public ExamTypeDto toDto(ExamTypeEntity entity) {
        return ExamTypeDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
