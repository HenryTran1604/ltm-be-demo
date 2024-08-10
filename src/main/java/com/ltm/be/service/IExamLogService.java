package com.ltm.be.service;

import com.ltm.be.dto.ExamLogDto;
import com.ltm.be.entity.ExamLogEntity;
import com.ltm.be.payload.request.webhook.ExamLogRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamLogService extends IBaseService<ExamLogDto, ExamLogEntity, Long> {
    ExamLogDto create(ExamLogRequest request);
    PageResponse<?> getExamLogByUser(Long examId, Long userId, int pageNo, int pageSize);
    void clearLogs();
}
