package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IContestScoreBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/contests")
public class ContestScoreBoardController {
    private final IContestScoreBoardService scoreBoardService;
    @GetMapping("/scoreboard/all")
    public ResponseData<?> getAllScoreBoard(@RequestParam Long contestId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "scoreboard",
                scoreBoardService.getAllScoreBoardByContestId(contestId));
    }
    @GetMapping("/scoreboard")
    public ResponseData<?> getScoreBoardByUserIdAndContestId(@RequestParam Long userId, @RequestParam(defaultValue = "1") Long contestId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Scoreboard",
                scoreBoardService.getScoreBoardByContestIdAndUserId(userId, contestId));
    }
}
