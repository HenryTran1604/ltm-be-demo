package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IContestLogService;
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
@Tag(name = "Contest log Controller")
public class ClientLogController {
    private final IContestLogService contestLogService;

    @Operation(
            summary = "Get logs in contest of user"
    )
    @GetMapping("/contest")
    public ResponseData<?> getContestLogsByUser(@RequestParam Long contestId,
                                                @RequestParam Long userId,
                                                @RequestParam(defaultValue = "0") int pageNo,
                                                @Min(1) @RequestParam(defaultValue = "1000") int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Client logs",
                contestLogService.getContestLogByUser(contestId, userId, pageNo, pageSize));
    }

    @Operation(
            summary = "Delete client communication logs"
    )
    @DeleteMapping("/clear-logs")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> clearLog() {
        contestLogService.clearLogs();
        return new ResponseData<>(HttpStatus.OK.value(), "Clear client logs successfully!");
    }
}
