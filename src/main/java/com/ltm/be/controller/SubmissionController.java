package com.ltm.be.controller;

import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.service.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class SubmissionController {
    @Autowired
    private ISubmissionService submissionService;

    @GetMapping("/submissions/user/{id}")
    public List<SubmissionDto> getAllByUserId(@PathVariable Long id) {
        return submissionService.getAllByUserId(id);
    }
    @GetMapping("/submissions/")
    public List<SubmissionDto> getAllLastSubmissionsByUserIdAndAllExercises(@RequestParam Long userId) {
        return  null;
    }
}
