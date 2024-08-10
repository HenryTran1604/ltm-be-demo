package com.ltm.be.service.impl;

import com.ltm.be.dto.ExamRankDto;
import com.ltm.be.payload.request.webhook.ExamLogRequest;
import com.ltm.be.payload.request.webhook.ExamRankRequest;
import com.ltm.be.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketServiceImpl implements IWebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IExamRankService examRankService;
    private final IExamLogService examLogService;
    @Override
    public void sendExamLog(ExamLogRequest request) {
        String destination = String.format("/topic/exam/%s/%s/%s/logs", request.getIp(), request.getUsername(), request.getExamId());
        simpMessagingTemplate.convertAndSend(destination,  examLogService.create(request));
    }

    @Override
    public void sendUpdatedExamRank(ExamRankRequest request) {
        ExamRankDto leaderBoard = examRankService.getByExamIdAndUserId(request.getExamId(), request.getExamId());
        String destination = String.format("/topic/exam/%s/%s/%s/scoreboard", request.getIp(), request.getUsername(), request.getExamId());
        simpMessagingTemplate.convertAndSend(destination, leaderBoard);
    }
}
