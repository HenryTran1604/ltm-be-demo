package com.ltm.be.service;

import com.ltm.be.dto.ExamUserExerciseDto;
import com.ltm.be.entity.ExamUserExerciseEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamUserExerciseService extends IBaseService<ExamUserExerciseDto, ExamUserExerciseEntity, Long> {
    PageResponse<?> getExercisesAssignedToUser(Long userId, Long examId, int pageNo, int pageSize);
}
