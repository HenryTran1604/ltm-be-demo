package com.ltm.be.controller;

import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.service.IUserExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserExerciseController {
    @Autowired
    private IUserExerciseService userExerciseService;

    @GetMapping("/user/exercise")
    public List<UserExerciseDto> getAllUserExercisesById(@RequestParam Long userId) {
        return userExerciseService.getScoreBoardsByUserId(userId);
    }
}
