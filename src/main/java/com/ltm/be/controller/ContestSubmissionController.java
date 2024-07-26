package com.ltm.be.controller;

import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.payload.response.ResponseError;
import com.ltm.be.service.IContestSubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@Tag(name = "Submission Controller")
public class ContestSubmissionController {
    private final IContestSubmissionService submissionService;

    @Operation(summary = "Get submission of user")
    @GetMapping("/user") // undone
    public ResponseData<?> getAllByUserIdAndContestId(@RequestParam Long id,
                                                      @RequestParam Long contestId,
                                                      @RequestParam(defaultValue = "0") int pageNo,
                                                      @RequestParam(defaultValue = "30") int pageSize) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Submission of user id = " + id,
                    submissionService.getAllByUserIdAndContestId(id, pageNo, pageSize));

        } catch (ResourceNotFoundException exception) {
            return new ResponseError<>(HttpStatus.NOT_FOUND.value(), "User id = " + id + " not exist");
        }
    }
}
