package com.ltm.be.converter;

import com.ltm.be.dto.PracticeScoreBoardDto;
import com.ltm.be.entity.PracticeUserExerciseEntity;
import com.ltm.be.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PracticeScoreBoardConverter {
    private final PracticeUserExerciseConverter practiceUserExerciseConverter;

    public PracticeScoreBoardDto toDto(UserEntity entity) {
        PracticeScoreBoardDto dto = new PracticeScoreBoardDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setIp(entity.getIp());
        dto.setScore((int) entity.getPracticeUserExercises().stream()
                .filter(PracticeUserExerciseEntity::isAc).count());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setPracticeUserExercises(entity.getPracticeUserExercises().stream().map(practiceUserExerciseConverter::toDto).toList());
        return dto;
    }
}
