package com.ltm.be.service.impl;

import com.ltm.be.dto.ScoreBoardDto;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.IScoreBoardService;
import com.ltm.be.service.IWebSocketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketServiceImpl implements IWebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IScoreBoardService scoreBoardService;

    @Override
    public void sendLog(String logMessage) {
        log.info(logMessage);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String formattedLogMessage = timestamp + " - " + logMessage;
        simpMessagingTemplate.convertAndSend("/topic/log", formattedLogMessage);
    }
    @Override
    public void sendUpdatedScoreBoard() {
        List<ScoreBoardDto> leaderBoard = scoreBoardService.getAllScoreBoard();
        simpMessagingTemplate.convertAndSend("/topic/scoreboard", leaderBoard);
    }
}
