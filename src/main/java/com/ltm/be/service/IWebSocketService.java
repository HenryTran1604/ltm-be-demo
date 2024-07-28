package com.ltm.be.service;

import com.ltm.be.payload.request.webhook.ContestLogRequest;
import com.ltm.be.payload.request.webhook.ContestScoreBoardRequest;
import com.ltm.be.payload.request.webhook.PracticeLogRequest;
import com.ltm.be.payload.request.webhook.PracticeScoreBoardRequest;

public interface IWebSocketService {
    void sendContestLog(ContestLogRequest request);
    void sendPracticeLog(PracticeLogRequest request);
    void sendUpdatedContestScoreBoard(ContestScoreBoardRequest request);
    void sendUpdatedPracticeScoreBoard(PracticeScoreBoardRequest request);

}
