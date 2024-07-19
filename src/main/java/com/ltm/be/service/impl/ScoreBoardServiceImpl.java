package com.ltm.be.service.impl;

import com.ltm.be.converter.ScoreBoardConverter;
import com.ltm.be.dto.ScoreBoardDto;
import com.ltm.be.entity.UserContestEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.repository.UserContestRepository;
import com.ltm.be.service.IScoreBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreBoardServiceImpl implements IScoreBoardService {
    private final UserContestRepository userContestRepository;
    private final ScoreBoardConverter scoreBoardConverter;
    @Override
    public List<ScoreBoardDto> getAllScoreBoardByContestId(Long contestId) {
        List<UserContestEntity> users = userContestRepository.findAllByContestId(contestId);
        return users.stream().map(scoreBoardConverter::toDto).toList();
    }

    @Override
    public ScoreBoardDto getScoreBoardByUserIdAndContestId(Long userId, Long contestId) {
        UserContestEntity user = userContestRepository.findByUserIdAndContestId(userId, contestId).orElseThrow(() -> new ResourceNotFoundException("User or contest not exist"));
        return scoreBoardConverter.toDto(user);
    }
}
