package com.ltm.be.controller;

import com.ltm.be.payload.request.webhook.ExamLogRequest;
import com.ltm.be.payload.request.webhook.ExamRankRequest;
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

    @PostMapping("/exam/logs")
    @Operation(
            summary = "Get client logs"
    )
    public void handleExamLogs(@RequestHeader("secret-token") String token,
                                 @RequestBody ExamLogRequest payload) {
        if (token.equals(webhookToken)) {
            webSocketService.sendExamLog(payload);
        }
    }


    @PostMapping("/practice/scoreboard")
    @Operation(
            summary = "get init scoreboard"
    )
    public void handleScoreboard(@RequestHeader("secret-token") String token,
                                 @RequestBody ExamRankRequest payload) {
        if(token.equals(webhookToken)) {
            webSocketService.sendUpdatedExamRank(payload);
        }
    }
}
