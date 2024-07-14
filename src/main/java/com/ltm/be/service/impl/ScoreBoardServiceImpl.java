package com.ltm.be.service.impl;

import com.ltm.be.converter.ScoreBoardConverter;
import com.ltm.be.dto.ScoreBoardDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IScoreBoardService;
import com.ltm.be.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScoreBoardServiceImpl implements IScoreBoardService {
    private final UserRepository userRepository;
    private final ScoreBoardConverter scoreBoardConverter;
    @Override
    public List<ScoreBoardDto> getAllScoreBoard() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(scoreBoardConverter::toDto).toList();
    }
}
