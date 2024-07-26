package com.ltm.be.service.impl;

import com.ltm.be.converter.ScoreBoardConverter;
import com.ltm.be.dto.ScoreBoardDto;
import com.ltm.be.entity.ContestUserEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.repository.ContestUserRepository;
import com.ltm.be.service.IScoreBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreBoardServiceImpl implements IScoreBoardService {
    private final ContestUserRepository contestUserRepository;
    private final ScoreBoardConverter scoreBoardConverter;
    @Override
    public List<ScoreBoardDto> getAllScoreBoardByContestId(Long contestId) {
        List<ContestUserEntity> users = contestUserRepository.findAllByContestId(contestId);
        return users.stream().map(scoreBoardConverter::toDto).toList();
    }

    @Override
    public ScoreBoardDto getScoreBoardByUserIdAndContestId(Long userId, Long contestId) {
        ContestUserEntity user = contestUserRepository.findByUserIdAndContestId(userId, contestId).orElseThrow(() -> new ResourceNotFoundException("User or contest not exist"));
        return scoreBoardConverter.toDto(user);
    }
}
