package com.ltm.be.service;

import com.ltm.be.dto.UserExerciseDto;

public interface IWebSocketService {
    void sendLog(String logMessage);
    void sendUpdatedScoreBoard(UserExerciseDto userExerciseDto);
}
