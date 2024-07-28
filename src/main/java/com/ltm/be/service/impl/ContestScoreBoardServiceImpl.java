package com.ltm.be.service.impl;

import com.ltm.be.converter.ContestScoreBoardConverter;
import com.ltm.be.dto.ContestScoreBoardDto;
import com.ltm.be.entity.ContestUserEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.repository.ContestUserRepository;
import com.ltm.be.service.IContestScoreBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContestScoreBoardServiceImpl implements IContestScoreBoardService {
    private final ContestUserRepository contestUserRepository;
    private final ContestScoreBoardConverter contestScoreBoardConverter;
    @Override
    public List<ContestScoreBoardDto> getAllScoreBoardByContestId(Long contestId) {
        List<ContestUserEntity> users = contestUserRepository.findAllByContestId(contestId);
        return users.stream().map(contestScoreBoardConverter::toDto).toList();
    }

    @Override
    public ContestScoreBoardDto getScoreBoardByContestIdAndUserId(Long userId, Long contestId) {
        ContestUserEntity user = contestUserRepository.findByUserIdAndContestId(userId, contestId).orElseThrow(() -> new ResourceNotFoundException("User or contest not exist"));
        return contestScoreBoardConverter.toDto(user);
    }

    @Override
    public ContestScoreBoardDto getScoreBoardByContestUserId(Long contestUserId) {
        ContestUserEntity user = contestUserRepository.findById(contestUserId).orElseThrow(() -> new ResourceNotFoundException("user not in this contest"));
        return contestScoreBoardConverter.toDto(user);
    }
}
