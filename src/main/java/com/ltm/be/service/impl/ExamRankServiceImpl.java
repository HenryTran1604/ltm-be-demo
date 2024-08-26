package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamRankConverter;
import com.ltm.be.dto.ExamRankDto;
import com.ltm.be.entity.ExamUserEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamUserRepository;
import com.ltm.be.service.IExamRankService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamRankServiceImpl extends BaseServiceImpl<ExamRankDto, ExamUserEntity> implements IExamRankService {
    private final ExamUserRepository examUserRepository;
    private final ExamRankConverter examRankConverter;

    public ExamRankServiceImpl(ExamUserRepository examUserRepository,
                               ExamRankConverter examRankConverter) {
        super(examUserRepository, examRankConverter);
        this.examUserRepository = examUserRepository;
        this.examRankConverter = examRankConverter;
    }

    @Override
    public PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamUserEntity> examUsers = examUserRepository.findAllByExamId(examId, pageable);
        return getPageByList(examUsers, pageNo, pageSize);
    }

    @Override
    public ExamRankDto getByExamIdAndUserId(Long userId, Long examId) {
        ExamUserEntity user = examUserRepository.findByUserIdAndExamId(userId, examId).orElseThrow(() -> new ResourceNotFoundException("User or exam not exist"));
        return examRankConverter.toDto(user);
    }
}
