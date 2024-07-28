package com.ltm.be.controller;

import com.ltm.be.payload.request.webhook.ContestLogRequest;
import com.ltm.be.payload.request.webhook.PracticeLogRequest;
import com.ltm.be.payload.request.webhook.PracticeScoreBoardRequest;
import com.ltm.be.service.IWebSocketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/webhook")
@Tag(name = "Webhook Controller")
public class WebhookController {
    private final IWebSocketService webSocketService;
    @Value("${webhook.token}")
    private String webhookToken;

    @PostMapping("/contest/logs")
    @Operation(
            summary = "Get client logs"
    )
    public void handleContestLogs(@RequestHeader("secret-token") String token,
                                 @RequestBody ContestLogRequest payload) {
        if (token.equals(webhookToken)) {
            webSocketService.sendContestLog(payload);
        }
    }

    @PostMapping("/practice/logs")
    @Operation(
            summary = "Get client logs"
    )
    public void handlePracticeLogs(@RequestHeader("secret-token") String token,
                                  @RequestBody PracticeLogRequest payload) {
        if (token.equals(webhookToken)) {
            webSocketService.sendPracticeLog(payload);
        }
    }


    @PostMapping("/practice/scoreboard")
    @Operation(
            summary = "get init scoreboard"
    )
    public void handleScoreboard(@RequestHeader("secret-token") String token,
                                 @RequestBody PracticeScoreBoardRequest payload) {
        if(token.equals(webhookToken)) {
            webSocketService.sendUpdatedPracticeScoreBoard(payload);
        }
    }
}
