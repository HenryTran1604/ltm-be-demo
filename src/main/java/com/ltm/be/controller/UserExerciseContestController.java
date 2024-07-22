package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IUserExerciseContestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-exercise-contest")
@Validated
@RequiredArgsConstructor
@Tag(name = "User Exercise Contest Controller")
public class UserExerciseContestController {
    private final IUserExerciseContestService userExerciseContestService;

    @GetMapping("/detail")
    public ResponseData<?> getUserExercisesByUser(@RequestParam Long userId,
                                                  @RequestParam Long contestId,
                                                  @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                  @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Assign successfully!",
                userExerciseContestService.getExercisesAssignedToUser(userId, contestId, pageNo, pageSize));
    }
}
