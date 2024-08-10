package com.ltm.be.service.impl;

import com.ltm.be.converter.AbstractBaseConverter;
import com.ltm.be.converter.ExamRankConverter;
import com.ltm.be.dto.ExamRankDto;
import com.ltm.be.entity.ExamUserEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.repository.BaseRepository;
import com.ltm.be.repository.ExamUserRepository;
import com.ltm.be.service.IExamRankService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExamRankServiceImpl implements IExamRankService {
    private final ExamUserRepository examUserRepository;
    private final ExamRankConverter examRankConverter;


    @Override
    public List<ExamRankDto> getAllByExamId(Long examId) {
        List<ExamUserEntity> users = examUserRepository.findAllByExamId(examId);
        return users.stream().map(examRankConverter::toDto).toList();
    }

    @Override
    public ExamRankDto getByExamIdAndUserId(Long userId, Long examId) {
        ExamUserEntity user = examUserRepository.findByUserIdAndExamId(userId, examId).orElseThrow(() -> new ResourceNotFoundException("User or exam not exist"));
        return examRankConverter.toDto(user);
    }
}
