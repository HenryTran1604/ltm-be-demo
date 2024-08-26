package com.ltm.be.controller;

import com.ltm.be.payload.request.ExamRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exams")
@Tag(name = "Exam controller")
@RequiredArgsConstructor
public class ExamController {
    private final IExamService examService;

    @GetMapping("/detail/{id}")
    public ResponseData<?> get(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "exam",
                examService.get(id));
    }
    @GetMapping("/all")
    public ResponseData<?> getAll() {
        return new ResponseData<>(HttpStatus.OK.value(),
                "exam",
                examService.getAll());
    }

    @PutMapping("/update")
    public ResponseData<?> update(@RequestParam Long id, @RequestBody ExamRequest request) {
        examService.update(id, request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Update successfully!");
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> create(@RequestBody ExamRequest request) {
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
