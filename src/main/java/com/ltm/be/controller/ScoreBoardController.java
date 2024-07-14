package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IScoreBoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ScoreBoardController {
    private final IScoreBoardService scoreBoardService;
    @GetMapping("/scoreboard")
    public ResponseData<?> getAllScoreBoard() {
        return new ResponseData<>(HttpStatus.OK.value(),
                "scoreboard",
                scoreBoardService.getAllScoreBoard());
    }
}
