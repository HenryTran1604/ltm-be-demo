package com.ltm.be.service;

import com.ltm.be.dto.UserDto;

public interface ILogService {
    void sendLog(String logMessage);
    String formatLog(String message);
}
