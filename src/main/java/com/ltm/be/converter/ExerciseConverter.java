package com.ltm.be.converter;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.entity.ExerciseEntity;
import org.springframework.stereotype.Component;

@Component
public class ExerciseConverter {
    public ExerciseDto toDto(ExerciseEntity entity) {
        ExerciseDto dto = new ExerciseDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
    public ExerciseEntity toEntity (ExerciseDto dto) {
        ExerciseEntity entity = new ExerciseEntity();
        entity.setName(dto.getName());
        entity.setContent(dto.getContent());
        return entity;
    }
}
