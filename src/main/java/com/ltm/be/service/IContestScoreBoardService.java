package com.ltm.be.service;

import com.ltm.be.dto.ContestScoreBoardDto;

import java.util.List;

public interface IContestScoreBoardService {
    List<ContestScoreBoardDto> getAllScoreBoardByContestId(Long contestId);
    ContestScoreBoardDto getScoreBoardByContestIdAndUserId(Long userId, Long contestId);
    ContestScoreBoardDto getScoreBoardByContestUserId(Long contestUserId);
}
