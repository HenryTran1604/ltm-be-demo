package com.ltm.be.converter;

import com.ltm.be.dto.ClientLogDto;
import com.ltm.be.entity.ClientLogEntity;
import org.springframework.stereotype.Component;

@Component
public class ClientLogConverter {
    public ClientLogDto toDto(ClientLogEntity entity) {
        ClientLogDto dto = new ClientLogDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}
