package com.ltm.be.service.impl;

import com.ltm.be.dto.ScoreBoardDto;
import com.ltm.be.service.IClientLogService;
import com.ltm.be.service.IScoreBoardService;
import com.ltm.be.service.IWebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketServiceImpl implements IWebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IScoreBoardService scoreBoardService;
    private final IClientLogService clientLogService;

    @Override
    public void sendLog(String logMessage) {
        log.info(logMessage);

        simpMessagingTemplate.convertAndSend("/topic/log", clientLogService.saveLog(logMessage));
    }
    @Override
    public void sendUpdatedScoreBoard(Long userId) {
        ScoreBoardDto leaderBoard = scoreBoardService.getScoreBoardByUserIdAndContestId(userId, 1L);
        simpMessagingTemplate.convertAndSend("/topic/scoreboard/"+userId, leaderBoard);
    }
}
