package com.ltm.be.controller;

import com.ltm.be.payload.request.ContestRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contests")
@RequiredArgsConstructor
public class ContestController {
    private final IContestService contestService;

    @GetMapping("/{id}")
    public ResponseData<?> getContestById(@PathVariable Long id) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Contest",
                contestService.getContestById(id));
    }
    @GetMapping("/all")
    public ResponseData<?> getAllContest() {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Contest",
                contestService.getAllContest());
    }

    @PutMapping("/update")
    public ResponseData<?> updateContestById(@RequestParam Long id, @RequestBody ContestRequest request) {
        contestService.update(id, request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Update successfully!");
    }


    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> addContest(@RequestBody ContestRequest request) {
        contestService.create(request);
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "Add contest successfully!"
        );
    }

    @PostMapping("/assign")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseData<?> assignExercisesToUserRandomly(@RequestBody Long contestId) {
        contestService.assignExercisesToUsers(contestId);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Assign exercise successfully!"
        );
    }
}
