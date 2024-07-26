package com.ltm.be.controller;

import com.ltm.be.payload.request.ContestRegistrationRequest;
import com.ltm.be.payload.request.ContestUserRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IContestService;
import com.ltm.be.service.IContestUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user-contest")
@RequiredArgsConstructor
@Tag(name = "User contest Controller")
public class ContestUserController {
    private final IContestUserService userContestService;
    private final IContestService contestService;

    @PostMapping("/add-all")
    public ResponseData<?> addUsersToContests(@Valid @RequestBody ContestUserRequest request) {
        userContestService.addUsersToContest(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Add users to contest successfully!");
    }

    @GetMapping("/all")
    public ResponseData<?> getAllContestWithRegistration(@RequestParam Long userId,
                                                         @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                         @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "List contest with registration",
                userContestService.getAllContestWithRegistration(userId, pageNo, pageSize));
    }

    @PostMapping("/register")
    public ResponseData<?> registerContest(@RequestBody ContestRegistrationRequest request) {
        userContestService.registerContest(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Register successfully!");
    }

    @GetMapping("/users")
    public ResponseData<?> getRegisteredUserByContestId(@RequestParam Long contestId,
                                                        @RequestParam(defaultValue = "0", required = false) int pageNo,
                                                        @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "users",
                userContestService.getAllRegisteredUserByContestId(contestId, pageNo, pageSize));
    }
}
