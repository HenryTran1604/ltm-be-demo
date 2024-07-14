package com.ltm.be.controller;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.ILogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Log Controller")
public class LogController {
    private final ILogService logService;
    @Operation(
            summary = "Get client communication logs"
    )
    @GetMapping("/client-logs")
    public ResponseData<?> getAllInfoLogs() {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Client logs",
                logService.getAllClientLogs());
    }
    @Operation(
            summary = "Delete client communication logs"
    )
    @DeleteMapping("/clear-logs")
    public ResponseData<?> clearLog() {
        logService.clearLogs();
        return new ResponseData<>(HttpStatus.OK.value(), "Clear client logs successfully!");
    }
}
