package com.ltm.be.service;

public interface IWebSocketService {
    void sendLog(String logMessage);
    void sendUpdatedScoreBoard(Long userId);
}
