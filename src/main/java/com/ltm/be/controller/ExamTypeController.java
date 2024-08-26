package com.ltm.be.controller;

import com.ltm.be.payload.request.ExamTypeRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamService;
import com.ltm.be.service.IExamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam-types")
public class ExamTypeController {
    private final IExamTypeService examTypeService;
    @PostMapping("/add")
    public ResponseData<?> addExamTypes(@RequestBody ExamTypeRequest request) {
        return new ResponseData<>(
                HttpStatus.CREATED.value(),
                "Add exam type successfully!"
        );
    }
    @GetMapping("/all")
    public ResponseData<?> getAll() {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Exam types",
                examTypeService.getAll());
    }
}
