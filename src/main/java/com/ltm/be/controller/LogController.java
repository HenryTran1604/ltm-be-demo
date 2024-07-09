package com.ltm.be.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class LogController {

    @GetMapping("/log-message")
    public List<String> logMessage() {
        List<String> lines = null;
        try {
            lines = Files.readAllLines(Paths.get("logs/application.log"));
        } catch (IOException e) {
            e.printStackTrace();
            // TODO: handle
        }
        return lines;
    }
}
