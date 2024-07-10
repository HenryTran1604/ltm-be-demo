package com.ltm.be.service;

import java.util.List;

public interface ILogService {
    List<String> getAllClientLogs();
    void clearLogs();
}
