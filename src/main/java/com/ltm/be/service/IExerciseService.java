package com.ltm.be.service;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.payload.request.ExerciseRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IExerciseService {
    PageResponse<?> getAllExercises(int pageNo, int pageSize);
    ExerciseDto getAllExerciseById(Long id);
    void addExercise(ExerciseRequest request);
}
