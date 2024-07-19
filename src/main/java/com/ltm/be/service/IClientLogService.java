package com.ltm.be.service;

import com.ltm.be.dto.ClientLogDto;
import com.ltm.be.payload.response.PageResponse;

public interface IClientLogService {
    ClientLogDto saveLog(String message);
    PageResponse<?> getAllClientLogs(int pageNo, int pageSize);

    void clearLogs();
}
