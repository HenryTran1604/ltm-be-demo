package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IPracticeLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/practice")
@RequiredArgsConstructor
@Tag(name = "Contest log Controller")
public class PracticeLogController {
    private final IPracticeLogService practiceLogService;

    @Operation(
            summary = "Get logs in contest of user"
    )
    @GetMapping("/logs")
    public ResponseData<?> getPracticeLogsByUser(@RequestParam Long userId,
                                                 @RequestParam(defaultValue = "0") int pageNo,
                                                 @Min(1) @RequestParam(defaultValue = "1000") int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Client logs",
                practiceLogService.getPracticeLogByUser(userId, pageNo, pageSize));
    }
}
