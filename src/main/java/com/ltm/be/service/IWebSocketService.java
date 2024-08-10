package com.ltm.be.service;

import com.ltm.be.payload.request.webhook.ExamLogRequest;
import com.ltm.be.payload.request.webhook.ExamRankRequest;

public interface IWebSocketService {
    void sendExamLog(ExamLogRequest request);
    void sendUpdatedExamRank(ExamRankRequest request);

}
