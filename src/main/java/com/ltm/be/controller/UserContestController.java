package com.ltm.be.controller;

import com.ltm.be.payload.request.UserContestRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IUserContestService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user-contest")
@RequiredArgsConstructor
@Tag(name = "User contest Controller")
public class UserContestController {
    private final IUserContestService userContestService;

    @PostMapping("/add-all")
    public ResponseData<?> addUsersToContests(@RequestBody  UserContestRequest request) {
        userContestService.addUsersToContest(request);
        return new ResponseData<>(HttpStatus.OK.value(),
                "Add users to contest successfully!");
    }
}
