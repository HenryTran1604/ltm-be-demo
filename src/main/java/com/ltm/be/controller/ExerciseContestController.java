package com.ltm.be.controller;

import com.ltm.be.payload.request.ExerciseContestRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExerciseContestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercise-contest")
@RequiredArgsConstructor
@Tag(name = "Exercise contest Controller")
public class ExerciseContestController {
    private final IExerciseContestService exerciseContestService;

    @PostMapping("/add-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> addExercisesToContest(@RequestBody ExerciseContestRequest request) {
        exerciseContestService.addExercisesToContest(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Add exercises to contest successfully!");
    }
}
