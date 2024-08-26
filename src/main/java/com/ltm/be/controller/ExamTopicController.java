package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamTopicService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/exam-topics")
@Tag(name = "Exam topic Controller")
public class ExamTopicController {
    private final IExamTopicService examTopicService;

    @GetMapping("/all")
    public ResponseData<?> getAllExamTopicByExamId(@RequestParam Long examId,
                                                   @RequestParam(defaultValue = "0") int pageNo,
                                                   @RequestParam(defaultValue = "20") int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Exam topics",
                examTopicService.getPage(pageNo, pageSize));
    }
}
