package com.ltm.be.converter;

import com.ltm.be.dto.ContestLogDto;
import com.ltm.be.entity.ContestLogEntity;
import org.springframework.stereotype.Component;

@Component
public class ContestLogConverter {
    public ContestLogDto toDto(ContestLogEntity entity) {
        ContestLogDto dto = new ContestLogDto();
        dto.setId(entity.getId());
        dto.setContent(entity.getContent());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setContestId(entity.getContest().getId());
        dto.setContestUserId(entity.getContestUser().getId());
        return dto;
    }
}
