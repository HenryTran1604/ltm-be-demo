package com.ltm.be.converter;

import com.ltm.be.dto.AliasDto;
import com.ltm.be.entity.AliasEntity;
import org.springframework.stereotype.Component;

@Component
public class AliasConverter {
    public AliasDto toDto(AliasEntity entity) {
        return AliasDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .active(entity.isActive())
                .build();
    }
}
