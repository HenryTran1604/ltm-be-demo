package com.ltm.be.converter;

import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.entity.SubmissionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SubmissionConverter {
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ExerciseConverter exerciseConverter;
    public SubmissionDto toDto(SubmissionEntity entity) {
        SubmissionDto dto = new SubmissionDto();
        dto.setId(entity.getId());
        dto.setUserDto(userConverter.toDto(entity.getUser()));
        dto.setExerciseDto(exerciseConverter.toDto(entity.getExercise()));
        dto.setAC(entity.getAc());
        dto.setSrcPath(entity.getSrcPath());
        dto.setSubmittedAt(entity.getSubmittedAt());
        return dto;
    }
}
