package com.ltm.be.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {
    @MessageMapping("/log")
    @SendTo("/topic/log")
    public String sendMessage(String message) {
        return message;
    }
}
