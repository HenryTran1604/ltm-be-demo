package com.ltm.be.service;

import com.ltm.be.payload.request.ExamExerciseRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IExamExerciseService {
    void addExercisesToExam(ExamExerciseRequest request);

    PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize);
}
