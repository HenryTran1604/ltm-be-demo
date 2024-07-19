package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IScoreBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScoreBoardController {
    private final IScoreBoardService scoreBoardService;
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
                scoreBoardService.getScoreBoardByUserIdAndContestId(userId, contestId));
    }
}
