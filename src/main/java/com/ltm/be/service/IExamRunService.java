package com.ltm.be.service;

import com.ltm.be.dto.ExamRunDto;
import com.ltm.be.entity.ExamRunEntity;
import com.ltm.be.payload.request.webhook.ExamLogRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamRunService {
    ExamRunDto create(ExamLogRequest request);
    PageResponse<?> getAllByExamAndUser(Long examId, Long userId, int pageNo, int pageSize);
    PageResponse<?> getAllByExam(Long examId, int pageNo, int pageSize);
    void clearLogs();
}
