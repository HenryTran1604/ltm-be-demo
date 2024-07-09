package com.ltm.be.service;

import com.ltm.be.dto.ExerciseDto;

import java.util.List;

public interface IExerciseService {
    List<ExerciseDto> getAllExercises();
    ExerciseDto getAllExerciseById(Long id);
    ExerciseDto addExercise(ExerciseDto dto);
}
