package com.ltm.be.controller;

import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.payload.response.ResponseError;
import com.ltm.be.service.IExamSubmissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/submissions")
@RequiredArgsConstructor
@Tag(name = "Submission Controller")
public class ExamSubmissionController {
    private final IExamSubmissionService submissionService;

    @Operation(summary = "Get submission of user")
    @GetMapping("/user") // undone
    public ResponseData<?> getAllByUserIdAndExamId(@RequestParam Long id,
                                                      @RequestParam Long examId,
                                                      @RequestParam(defaultValue = "0") int pageNo,
                                                      @RequestParam(defaultValue = "30") int pageSize) {
        try {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "Submission of user id = " + id,
                    submissionService.getAllByUserIdAndExamId(id, pageNo, pageSize));

        } catch (ResourceNotFoundException exception) {
            return new ResponseError<>(HttpStatus.NOT_FOUND.value(), "User id = " + id + " not exist");
        }
    }
}
