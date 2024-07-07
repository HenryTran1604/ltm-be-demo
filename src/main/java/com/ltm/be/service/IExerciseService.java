package com.ltm.be.service;

import com.ltm.be.dto.ExerciseDto;

import java.util.List;

public interface IExerciseService {
    List<ExerciseDto> getAllExercises();
    ExerciseDto getExerciseByName(String name);
    ExerciseDto addExercise(ExerciseDto dto);

}
