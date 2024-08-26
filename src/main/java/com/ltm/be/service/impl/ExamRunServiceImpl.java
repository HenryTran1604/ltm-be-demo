package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamLogConverter;
import com.ltm.be.dto.ExamRunDto;
import com.ltm.be.entity.ExamRunEntity;
import com.ltm.be.payload.request.webhook.ExamLogRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamRunRepository;
import com.ltm.be.repository.ExamRepository;
import com.ltm.be.repository.ExamUserRepository;
import com.ltm.be.service.IExamRunService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamRunServiceImpl extends BaseServiceImpl<ExamRunDto, ExamRunEntity> implements IExamRunService {
    private final ExamRunRepository examRunRepository;
    private final ExamRepository examRepository;
    private final ExamUserRepository examUserRepository;

    public ExamRunServiceImpl(ExamRunRepository examRunRepository,
                              ExamLogConverter examLogConverter,
                              ExamRepository examRepository,
                              ExamUserRepository examUserRepository) {
        super(examRunRepository, examLogConverter);
        this.examRunRepository = examRunRepository;
        this.examRepository = examRepository;
        this.examUserRepository = examUserRepository;
    }

    @Override
    public ExamRunDto create(ExamLogRequest request) {
        ExamRunEntity clientLog = ExamRunEntity.builder()
                .examUser(examUserRepository.getReferenceById(request.getExamUserId()))
                .content(request.getMessage())
                .build();
        return create(clientLog);
    }

    @Override
    public PageResponse<?> getAllByExamAndUser(Long examId, Long userId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamRunEntity> clientLogs = examRunRepository.findAllByExamIdAndUserId(examId, userId, pageable);
        return getPageByList(clientLogs, pageNo, pageSize);
    }

    @Override
    public PageResponse<?> getAllByExam(Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamRunEntity> clientLogs = examRunRepository.findAllByExamId(examId, pageable);
        return getPageByList(clientLogs, pageNo, pageSize);
    }

    @Override
    public void clearLogs() {
        examRunRepository.deleteAll();
    }
}
