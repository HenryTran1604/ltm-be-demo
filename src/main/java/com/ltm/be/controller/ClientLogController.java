package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
@Tag(name = "exam log Controller")
public class ClientLogController {
    private final IExamLogService examLogService;

    @Operation(
            summary = "Get logs in exam of user"
    )
    @GetMapping("/exam")
    public ResponseData<?> getexamLogsByUser(@RequestParam Long examId,
                                             @RequestParam Long userId,
                                             @RequestParam(defaultValue = "0") int pageNo,
                                             @Min(1) @RequestParam(defaultValue = "1000") int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Client logs",
                examLogService.getExamLogByUser(examId, userId, pageNo, pageSize));
    }

    @Operation(
            summary = "Delete client communication logs"
    )
    @DeleteMapping("/clear-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> clearLog() {
        examLogService.clearLogs();
        return new ResponseData<>(HttpStatus.OK.value(), "Clear client logs successfully!");
    }
}
