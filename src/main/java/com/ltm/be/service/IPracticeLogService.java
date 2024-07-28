package com.ltm.be.service;

import com.ltm.be.dto.PracticeLogDto;
import com.ltm.be.payload.request.webhook.PracticeLogRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IPracticeLogService {
    PracticeLogDto savePracticeLog(PracticeLogRequest request);
    PageResponse<?> getPracticeLogByUser(Long userId, int pageNo, int pageSize);
    void clearLogs();
}
