package com.ltm.be.controller;

import com.ltm.be.payload.request.ExerciseContestRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExerciseContestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercise-contest")
@RequiredArgsConstructor
@Tag(name = "Exercise contest Controller")
public class ExerciseContestController {
    private final IExerciseContestService exerciseContestService;

    @PostMapping("/add-all")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> addExercisesToContest(@Valid @RequestBody ExerciseContestRequest request) {
        exerciseContestService.addExercisesToContest(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Add exercises to contest successfully!");
    }

    @GetMapping("/exercises")
    public ResponseData<?> getAddedExercisesByContestId(@RequestParam Long contestId,
                                                             @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                             @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "users",
                exerciseContestService.getAllAddedExercisesByContestId(contestId, pageNo, pageSize));
    }
}
