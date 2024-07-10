package com.ltm.be.service.impl;

import com.ltm.be.config.StorageProperties;
import com.ltm.be.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
public class LogServiceImpl implements ILogService {
    private static final Logger logger = LoggerFactory.getLogger(LogServiceImpl.class);
    @Autowired
    private StorageProperties storageProperties;
    @Override
    public List<String> getAllClientLogs() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(this.storageProperties.getClientLogsLocation()));
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return lines;
    }

    @Override
    public void clearLogs() {
        try {
            Files.write(Paths.get(this.storageProperties.getClientLogsLocation()), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }
}
