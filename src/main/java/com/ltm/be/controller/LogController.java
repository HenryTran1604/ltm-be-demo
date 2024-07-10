package com.ltm.be.controller;
import com.ltm.be.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LogController {
    @Autowired
    private ILogService logService;
    @GetMapping("/client-logs")
    public List<String> getAllInfoLogs() {
        return logService.getAllClientLogs();
    }

    @DeleteMapping("/clear-logs")
    public ResponseEntity<?> clearLog() {
        logService.clearLogs();
        return ResponseEntity.ok().build();
    }
}
