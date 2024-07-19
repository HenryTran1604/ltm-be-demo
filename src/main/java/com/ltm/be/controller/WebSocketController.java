package com.ltm.be.controller;

import org.springframework.context.annotation.Profile;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
@Profile("!prod")
public class WebSocketController {
    @MessageMapping("/log")
    @SendTo("/topic/log")
    public String sendMessage(String message) {
        return message;
    }
}
