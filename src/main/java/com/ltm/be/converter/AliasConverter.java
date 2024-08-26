package com.ltm.be.converter;

import com.ltm.be.dto.AliasDto;
import com.ltm.be.entity.AliasEntity;
import org.springframework.stereotype.Component;

@Component
public class AliasConverter extends AbstractBaseConverter<AliasDto, AliasEntity> {
    @Override
    public AliasDto toDto(AliasEntity entity) {
        return AliasDto.builder()
                .id(entity.getId())
                .code(entity.getCode())
                .active(entity.isActive())
                .createdAt(entity.getCreatedAt())
                .build();
    }
}
