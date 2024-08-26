package com.ltm.be.service;

import com.ltm.be.dto.ExamDetailDto;
import com.ltm.be.entity.ExamDetailEntity;
import com.ltm.be.payload.request.ExamExerciseRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamDetailService extends IBaseService<ExamDetailDto, ExamDetailEntity> {
    void addExercisesToExam(ExamExerciseRequest request);
    PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize);
}
