package com.ltm.be.service;

import com.ltm.be.payload.request.ExamDetailRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IExamDetailService {
    void create(ExamDetailRequest request);
    PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize);
}
