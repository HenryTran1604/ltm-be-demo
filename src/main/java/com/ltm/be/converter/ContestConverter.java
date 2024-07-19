package com.ltm.be.converter;

import com.ltm.be.dto.ContestDto;
import com.ltm.be.entity.ContestEntity;
import org.springframework.stereotype.Component;

@Component
public class ContestConverter {
    public ContestDto toDto(ContestEntity entity) {
        ContestDto dto = new ContestDto();
        dto.setId(entity.getId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
