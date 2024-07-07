package com.ltm.be.service.impl;

import com.ltm.be.dto.UserDto;
import com.ltm.be.service.IScoreBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class ScoreBoardServiceImpl implements IScoreBoardService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;
    @Override
    public void sendUpdatedScoreBoard(UserDto userDto) {
        simpMessagingTemplate.convertAndSend("/topic/scoreboard", userDto);
    }
}
