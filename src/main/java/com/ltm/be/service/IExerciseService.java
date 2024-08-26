package com.ltm.be.service;

import com.ltm.be.dto.QuestionDto;
import com.ltm.be.entity.QuestionEntity;
import com.ltm.be.payload.request.ExerciseRequest;
import com.ltm.be.service.base.IBaseService;

public interface IExerciseService extends IBaseService<QuestionDto, QuestionEntity> {
    void create(ExerciseRequest request);
    void update(Long id, ExerciseRequest request);
}
