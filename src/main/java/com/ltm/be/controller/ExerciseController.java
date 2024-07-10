package com.ltm.be.controller;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ExerciseController {
    @Autowired
    private IExerciseService exerciseService;
    @PostMapping("/exercise/add")
    public ResponseEntity<?> addExercise(@RequestBody ExerciseDto exerciseDto) {
        exerciseService.addExercise(exerciseDto);
        return ResponseEntity.ok().build();

    }
}
