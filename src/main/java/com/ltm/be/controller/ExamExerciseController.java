package com.ltm.be.controller;

import com.ltm.be.payload.request.ExamExerciseRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-exercises")
@RequiredArgsConstructor
@Tag(name = "Exercise exam Controller")
public class ExamExerciseController {
    private final IExamDetailService exerciseExamService;

    @PostMapping("/add-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> create(@Valid @RequestBody ExamExerciseRequest request) {
        exerciseExamService.addExercisesToExam(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Add exercises to exam successfully!");
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> getAll(@RequestParam Long examId,
                                  @RequestParam(defaultValue = "0", required = false) int pageNo,
                                  @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "users",
                exerciseExamService.getAllByExamId(examId, pageNo, pageSize));
    }
}
