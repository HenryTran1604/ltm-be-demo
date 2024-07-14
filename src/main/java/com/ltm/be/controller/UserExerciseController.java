package com.ltm.be.controller;

import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IUserExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "User Exercise Controller")
public class UserExerciseController {
    private final IUserExerciseService userExerciseService;
    @Operation(
            summary = "Get all exercise of user",
            description = "Just exercises that user submitted"
    )
    @GetMapping("/user/exercise")
    public ResponseData<?> getAllUserExercisesById(@RequestParam Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Exercise of user Id=" + userId,
                userExerciseService.getUserExercisesByUserId(userId)
        );
    }
}
