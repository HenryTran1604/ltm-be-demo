package com.ltm.be.service.impl;

import com.ltm.be.config.StorageProperties;
import com.ltm.be.service.ILogService;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class LogServiceImpl implements ILogService {
    @Autowired
    private StorageProperties storageProperties;

    @Override
    public void addClientLogs(String... logs) {
        for(String message : logs) {
            log.info(message);
        }
    }

    @Override
    public List<String> getAllClientLogs() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get(this.storageProperties.getClientLogsLocation()));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return lines;
    }

    @Override
    public void clearLogs() {
        try {
            Files.write(Paths.get(this.storageProperties.getClientLogsLocation()), new byte[0], StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
