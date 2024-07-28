package com.ltm.be.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!prod")
@RequiredArgsConstructor
public class WebSocketController {
    private final SimpMessagingTemplate simpMessagingTemplate;
    @MessageMapping("/log") // /app/log
    @SendTo("/topic/log")
    public String sendMessage(String message) {
        return message;
    }
    @MessageMapping("/practice")
    public void sendToSpecificUser(@Header("simpSessionId") String sessionId, String username) {
        System.out.println(username);
        System.out.println(sessionId);
        simpMessagingTemplate.convertAndSendToUser(username, "/queue/practice", "haha");
    }
}
