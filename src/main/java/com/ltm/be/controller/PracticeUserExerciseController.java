package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IPracticeUserExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/practice-user-exercise")
public class PracticeUserExerciseController {
    private final IPracticeUserExerciseService practiceUserExerciseService;
    @GetMapping("/detail")
    public ResponseData<?> getContestUserExercisesByUser(@RequestParam Long userId,
                                                         @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                         @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Practice exercises!",
                practiceUserExerciseService.getAllPracticeUserExerciseByUser(userId, pageNo, pageSize));
    }
}
