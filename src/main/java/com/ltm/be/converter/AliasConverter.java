package com.ltm.be.converter;

import com.ltm.be.dto.AliasDto;
import com.ltm.be.entity.AliasEntity;
import org.springframework.stereotype.Component;

@Component
public class AliasConverter {
    public AliasDto toDto(AliasEntity entity) {
        AliasDto dto = new AliasDto();
        dto.setId(entity.getId());
        dto.setCode(entity.getCode());
        dto.setExerciseId(entity.getExercise().getId());
        dto.setActive(entity.isActive());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
