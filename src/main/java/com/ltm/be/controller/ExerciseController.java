package com.ltm.be.controller;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.payload.request.ExerciseRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IExerciseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/exercises")
@RequiredArgsConstructor
@Tag(name = "Exercise Controller")
public class ExerciseController {
    private final IExerciseService exerciseService;
    @Operation(
            summary = "Add new exercise"
    )
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> addExercise(@RequestBody ExerciseRequest request) {
        exerciseService.addExercise(request);
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "Add exercises successfully!");
    }

    @Operation(
            summary = "Update exercise"
    )
    @PutMapping("/update")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> updateExercise(@RequestParam Long id, @RequestBody ExerciseRequest request) {
        exerciseService.updateExercise(id, request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Update exercises successfully!");
    }

    @GetMapping("/all")
    public ResponseData<?> getAllExercises(@RequestParam(defaultValue = "0") int pageNo,
                                           @Min(1) @RequestParam(defaultValue = "50") int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Exercises",
                exerciseService.getAllExercises(pageNo, pageSize));
    }

    @GetMapping("/detail/{id}")
    public ResponseData<?> getExercisesById(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Exercises",
                exerciseService.getAllExerciseById(id));
    }
}
