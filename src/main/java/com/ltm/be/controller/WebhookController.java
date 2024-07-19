package com.ltm.be.controller;

import com.ltm.be.service.ILogService;
import com.ltm.be.service.IWebSocketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/webhook")
@Tag(name = "Webhook Controller")
public class WebhookController {
    private final IWebSocketService webSocketService;
    @PostMapping("/log")
    @Operation(
            summary = "Get client logs"
    )
    public void handleClientLogs(@RequestBody String payload) {
        webSocketService.sendLog(payload);
    }
    @PostMapping("/scoreboard")
    @Operation(
            summary = "get init scoreboard"
    )
    public void handleScoreboard(@RequestBody String payload) {
        Long userId = Long.parseLong(payload);
        webSocketService.sendUpdatedScoreBoard(userId);
    }
}
