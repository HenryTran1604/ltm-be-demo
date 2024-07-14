package com.ltm.be.service;

import java.util.List;

public interface ILogService {
    void addClientLogs(String... logs);
    List<String> getAllClientLogs();
    void clearLogs();
}
