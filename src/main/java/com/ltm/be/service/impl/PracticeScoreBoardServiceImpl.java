package com.ltm.be.service.impl;

import com.ltm.be.converter.PracticeScoreBoardConverter;
import com.ltm.be.dto.PracticeScoreBoardDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IPracticeScoreBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PracticeScoreBoardServiceImpl implements IPracticeScoreBoardService {
    private final UserRepository userRepository;
    private final PracticeScoreBoardConverter practiceScoreBoardConverter;
    @Override
    public List<PracticeScoreBoardDto> getAllPracticeScoreBoard() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(practiceScoreBoardConverter::toDto).toList();
    }

    @Override
    public PracticeScoreBoardDto getScoreBoardByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        return practiceScoreBoardConverter.toDto(user);
    }
}
