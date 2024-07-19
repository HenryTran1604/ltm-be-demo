package com.ltm.be.converter;

import com.ltm.be.dto.ExerciseContestDto;
import com.ltm.be.dto.UserExerciseContestDto;
import com.ltm.be.entity.UserExerciseContestEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserExerciseContestConverter {
    private final ExerciseContestConverter exerciseContestConverter;
    public UserExerciseContestDto toDto(UserExerciseContestEntity entity) {
        UserExerciseContestDto dto = new UserExerciseContestDto();
        dto.setId(entity.getId());
        dto.setUserContestId(entity.getUserContest().getId());
        dto.setExerciseContestDto(exerciseContestConverter.toDto(entity.getExerciseContest()));
        dto.setAc(entity.isAc());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setAttemptCount(entity.getSubmissions().size());
        return dto;
    }
}
