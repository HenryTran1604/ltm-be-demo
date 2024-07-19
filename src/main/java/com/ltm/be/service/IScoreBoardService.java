package com.ltm.be.service;

import com.ltm.be.dto.ScoreBoardDto;

import java.util.List;
import java.util.Optional;

public interface IScoreBoardService {
    List<ScoreBoardDto> getAllScoreBoardByContestId(Long contestId);
    ScoreBoardDto getScoreBoardByUserIdAndContestId(Long userId, Long contestId);
}
