package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IClientLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "Client log Controller")
public class ClientLogController {
    private final IClientLogService clientLogService;
    @Operation(
            summary = "Get client communication logs"
    )
    @GetMapping("/client-logs")
    public ResponseData<?> getAllInfoLogs(@RequestParam(defaultValue = "0") int pageNo,
                                          @Min(1) @RequestParam(defaultValue = "1000") int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Client logs",
                clientLogService.getAllClientLogs(pageNo, pageSize));
    }
    @Operation(
            summary = "Delete client communication logs"
    )
    @DeleteMapping("/clear-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> clearLog() {
        clientLogService.clearLogs();
        return new ResponseData<>(HttpStatus.OK.value(), "Clear client logs successfully!");
    }
}
