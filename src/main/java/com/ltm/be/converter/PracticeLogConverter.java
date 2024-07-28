package com.ltm.be.converter;

import com.ltm.be.dto.PracticeLogDto;
import com.ltm.be.entity.PracticeLogEntity;
import org.springframework.stereotype.Component;

@Component
public class PracticeLogConverter {
    public PracticeLogDto toDto(PracticeLogEntity entity) {
        PracticeLogDto dto = new PracticeLogDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUserId(entity.getUser().getId());
        return dto;
    }
}
