package com.ltm.be.service;

import com.ltm.be.dto.QuestionDto;
import com.ltm.be.entity.QuestionEntity;
import com.ltm.be.payload.request.QuestionRequest;
import com.ltm.be.service.base.IBaseService;

import java.util.UUID;

public interface IExerciseService {
    void create(QuestionRequest request);
    void update(UUID id, Ques