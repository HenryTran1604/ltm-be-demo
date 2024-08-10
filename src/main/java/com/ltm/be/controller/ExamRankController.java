package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamRankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/exams")
public class ExamRankController {
    private final IExamRankService rankService;
    @GetMapping("/scoreboard/all")
    public ResponseData<?> getAllRank(@RequestParam Long examId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "scoreboard",
                rankService.getAllByExamId(examId));
    }
    @GetMapping("/scoreboard")
    public ResponseData<?> getRankByUserIdAndExamId(@RequestParam Long userId, @RequestParam(defaultValue = "1") Long examId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Scoreboard",
                rankService.getByExamIdAndUserId(userId, examId));
    }
}
