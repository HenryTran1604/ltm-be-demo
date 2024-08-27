package com.ltm.be.service;

import com.ltm.be.payload.request.ExamRegistrationRequest;
import com.ltm.be.payload.request.ExamUserRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IExamUserService {
    void create(ExamUserRequest request);

    void register(ExamRegistrationRequest request);

    PageResponse<?> getAllExamWithRegistration(Long userId, int pageNo, int pageSize);

    PageResponse<?> getAllRegisteredUserByExamId(Long examId, int pageNo, int pageSize);
}
