package com.ltm.be.controller;

import com.ltm.be.payload.request.ExamRegistrationRequest;
import com.ltm.be.payload.request.ExamUserRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-users")
@RequiredArgsConstructor
@Tag(name = "User exam Controller")
public class ExamUserController {
    private final IExamUserService examUserService;

    @PostMapping("/add-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> addUsersToExams(@Valid @RequestBody ExamUserRequest request) {
        examUserService.create(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Add users to exam successfully!");
    }

    @GetMapping("/all")
    public ResponseData<?> getAllExamWithRegistration(@RequestParam Long userId,
                                                         @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                         @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "List exam with registration",
                examUserService.getAllExamWithRegistration(userId, pageNo, pageSize));
    }

    @PostMapping("/register")
    public ResponseData<?> registerExam(@RequestBody ExamRegistrationRequest request) {
        examUserService.register(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Register successfully!");
    }

    @GetMapping("/users")
    public ResponseData<?> getRegisteredUserByExamId(@RequestParam Long examId,
                                                        @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                        @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "users",
                examUserService.getAllRegisteredUserByExamId(examId, pageNo, pageSize));
    }
}
