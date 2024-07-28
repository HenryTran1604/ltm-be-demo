package com.ltm.be.service;

import com.ltm.be.dto.ContestLogDto;
import com.ltm.be.payload.request.webhook.ContestLogRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IContestLogService {
    ContestLogDto saveContestLog(ContestLogRequest request);
    PageResponse<?> getContestLogByUser(Long contestId, Long userId, int pageNo, int pageSize);
    void clearLogs();
}
