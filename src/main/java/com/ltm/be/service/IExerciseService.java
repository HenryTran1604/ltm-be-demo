package com.ltm.be.service;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.payload.request.ExerciseRequest;
import com.ltm.be.service.base.IBaseService;

public interface IExerciseService extends IBaseService<ExerciseDto, ExerciseEntity, Long> {
    void create(ExerciseRequest request);
    void update(Long id, ExerciseRequest request);
}
