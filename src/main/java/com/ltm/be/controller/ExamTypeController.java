package com.ltm.be.controller;

import com.ltm.be.payload.request.ExamTypeRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamService;
import com.ltm.be.service.IExamTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam-types")
public class ExamTypeController {
    private final IExamTypeService examTypeService;
    @PostMapping("/add")
    public ResponseData<?> addExamTypes(@RequestBody ExamTypeRequest request) {
        examTypeService.create(request);
        return new ResponseData<>(
                HttpStatus.CREATED.value(),
                "Add exam type successfully!"
        );
    }
}
