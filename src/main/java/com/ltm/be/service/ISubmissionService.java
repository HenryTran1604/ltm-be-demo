package com.ltm.be.service;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.dto.UserDto;

import java.time.LocalDate;
import java.util.List;

public interface ISubmissionService {
    List<SubmissionDto> getAllByUserId(Long userId);
    void updateByUserAndExercise(Long userId, Long exerciseId, LocalDate submittedAt, Integer ac, String srcPath);
    Integer countByUserIdAndExerciseId(Long userId, Long exerciseId);
    boolean existsByUserIdAndExerciseIdAndAc(Long userId, Long exerciseId, Integer ac);

}
