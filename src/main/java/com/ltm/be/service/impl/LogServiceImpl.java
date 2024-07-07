package com.ltm.be.service.impl;

import com.ltm.be.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
@Service
public class LogServiceImpl implements ILogService {
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void sendLog(String logMessage) {
        logger.info(logMessage);

        String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String formattedLogMessage = timestamp + " - " + logMessage;
        simpMessagingTemplate.convertAndSend("/topic/log", formattedLogMessage);
    }
}
