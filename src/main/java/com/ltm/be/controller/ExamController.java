package com.ltm.be.controller;

import com.ltm.be.payload.request.ExamRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {
    private final IExamService examService;

    @GetMapping("/detail/{id}")
    public ResponseData<?> getExamById(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "exam",
                examService.get(id));
    }
    @GetMapping("/all")
    public ResponseData<?> getAllExam() {
        return new ResponseData<>(HttpStatus.OK.value(),
                "exam",
                examService.getAll());
    }

    @PutMapping("/update")
    public ResponseData<?> updateExamById(@RequestParam Long id, @RequestBody ExamRequest request) {
        examService.update(id, request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Update successfully!");
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> addExam(@RequestBody ExamRequest request) {
        examService.create(request);
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "Add exam successfully!"
        );
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> assignExercisesToUserRandomly(@RequestParam Long id) {
        examService.assignExercisesToUsers(id);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Assign exercise successfully!"
        );
    }
}
