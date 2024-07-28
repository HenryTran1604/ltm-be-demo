package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IContestScoreBoardService;
import com.ltm.be.service.IPracticeScoreBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/practice")
public class PracticeScoreBoardController {
    private final IPracticeScoreBoardService scoreBoardService;
    @GetMapping("/scoreboard/all")
    public ResponseData<?> getAllScoreBoard() {
        return new ResponseData<>(HttpStatus.OK.value(),
                "scoreboard",
                scoreBoardService.getAllPracticeScoreBoard());
    }
    @GetMapping("/scoreboard")
    public ResponseData<?> getScoreBoardByUserIdAndContestId(@RequestParam Long userId) {
        return new ResponseData<>(HttpStatus.OK.value(),
                "Scoreboard",
                scoreBoardService.getScoreBoardByUserId(userId));
    }
}
