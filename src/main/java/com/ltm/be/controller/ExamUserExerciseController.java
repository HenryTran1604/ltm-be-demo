package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExamUserDetailService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exam-user-exercise")
@Validated
@RequiredArgsConstructor
@Tag(name = "User Exercise exam Controller")
public class ExamUserExerciseController {
    private final IExamUserDetailService userExerciseExamService;

    @GetMapping("/detail")
    public ResponseData<?> getexamUserExercisesByUser(@RequestParam Long userId,
                                                         @RequestParam Long examId,
                                                         @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                         @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Assign successfully!",
                userExerciseExamService.getExercisesAssignedToUser(userId, examId, pageNo, pageSize));
    }
}
