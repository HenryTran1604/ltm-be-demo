package com.ltm.be.converter;

import com.ltm.be.dto.ContestScoreBoardDto;
import com.ltm.be.entity.ContestUserEntity;
import com.ltm.be.entity.ContestUserExerciseEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContestScoreBoardConverter {
    private final ContestUserExerciseConverter contestUserExerciseConverter;
    public ContestScoreBoardDto toDto(ContestUserEntity entity) {
        ContestScoreBoardDto dto = new ContestScoreBoardDto();
        dto.setId(entity.getId());
        dto.setUserContestId(entity.getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setIp(entity.getUser().getIp());
        dto.setScore((int) entity.getContestUserExercises().stream()
                .filter(ContestUserExerciseEntity::isAc)
                .count());
        dto.setContestUserExercises(entity.getContestUserExercises().stream().map(contestUserExerciseConverter::toDto).toList());
        return dto;
    }
}
