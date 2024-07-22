package com.ltm.be.converter;

import com.ltm.be.dto.ScoreBoardDto;
import com.ltm.be.entity.UserContestEntity;
import com.ltm.be.entity.UserExerciseContestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScoreBoardConverter {
    private final UserExerciseContestConverter userExerciseContestConverter;
    public ScoreBoardDto toDto(UserContestEntity entity) {
        ScoreBoardDto dto = new ScoreBoardDto();
        dto.setId(entity.getId());
        dto.setUserContestId(entity.getId());
        dto.setUsername(entity.getUser().getUsername());
        dto.setIp(entity.getUser().getIp());
        dto.setScore((int) entity.getUserExerciseContests().stream()
                .filter(UserExerciseContestEntity::isAc)
                .count());
        dto.setUserExerciseContests(entity.getUserExerciseContests().stream().map(userExerciseContestConverter::toDto).toList());
        return dto;
    }
}
