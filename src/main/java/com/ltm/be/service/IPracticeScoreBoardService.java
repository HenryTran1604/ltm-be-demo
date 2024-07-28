package com.ltm.be.service;

import com.ltm.be.dto.PracticeScoreBoardDto;

import java.util.List;

public interface IPracticeScoreBoardService {
    List<PracticeScoreBoardDto> getAllPracticeScoreBoard();
    PracticeScoreBoardDto getScoreBoardByUserId(Long userId);
}
