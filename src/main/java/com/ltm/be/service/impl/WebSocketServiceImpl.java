package com.ltm.be.service.impl;

import com.ltm.be.dto.ContestScoreBoardDto;
import com.ltm.be.dto.PracticeScoreBoardDto;
import com.ltm.be.payload.request.webhook.ContestLogRequest;
import com.ltm.be.payload.request.webhook.ContestScoreBoardRequest;
import com.ltm.be.payload.request.webhook.PracticeLogRequest;
import com.ltm.be.payload.request.webhook.PracticeScoreBoardRequest;
import com.ltm.be.service.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import static com.ltm.be.util.Constants.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebSocketServiceImpl implements IWebSocketService {
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final IContestScoreBoardService contestScoreBoardService;
    private final IContestLogService contestLogService;
    private final IPracticeLogService practiceLogService;
    private final IPracticeScoreBoardService practiceScoreBoardService;
    private String getStatusFromCode(int code) {
        if (code == ACCEPTED) {
            return "ACCEPTED";
        }
        if (code == WRONG_ANSWER) {
            return "WRONG ANSWER";
        }
        if (code == INVALID_FORMAT_INPUT) {
            return "INVALID FORMAT INPUT";
        }
        if (code == TIME_OUT) {
            return "TIME OUT";
        }
        if (code == REQUEST_WRONG_EXERCISE) {
            return "WRONG EXERCISE";
        }
        if (code == INVALID_USER) {
            return "INVALID USER";
        }
        if (code == FORBIDDEN) {
            return "FORBIDDEN";
        }
        return "UNKNOWN STATUS";
    }
    @Override
    public void sendContestLog(ContestLogRequest request) {
        String destination = String.format("/queue/contest/%d/logs", request.getContestId());
        String message = request.getAlias() + " " + getStatusFromCode(request.getCode()) + " " + request.getMessage();
        request.setMessage(message);
        simpMessagingTemplate.convertAndSend(destination,  contestLogService.saveContestLog(request));
    }

    @Override
    public void sendPracticeLog(PracticeLogRequest request) {
        String destination = String.format("/topic/practice/%s/logs", request.getUsername());
        String message = request.getAlias() + " " + getStatusFromCode(request.getCode()) + " " + request.getMessage();
        request.setMessage(message);
        simpMessagingTemplate.convertAndSend(destination, practiceLogService.savePracticeLog(request));

    }

    @Override
    public void sendUpdatedContestScoreBoard(ContestScoreBoardRequest request) {
        ContestScoreBoardDto leaderBoard = contestScoreBoardService.getScoreBoardByContestUserId(request.getContestUserId());
        String destination = String.format("/topic/contest/%s/%d/scoreboard", request.getUsername(), request.getContestId());
        simpMessagingTemplate.convertAndSend(destination, leaderBoard);
    }

    @Override
    public void sendUpdatedPracticeScoreBoard(PracticeScoreBoardRequest request) {
        PracticeScoreBoardDto leaderBoard = practiceScoreBoardService.getScoreBoardByUserId(request.getUserId());
        String destination = String.format("/topic/practice/%s/scoreboard", request.getUsername());
        simpMessagingTemplate.convertAndSend(destination, leaderBoard);
    }
}
