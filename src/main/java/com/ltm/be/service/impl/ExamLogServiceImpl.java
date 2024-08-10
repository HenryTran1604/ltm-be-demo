package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamLogConverter;
import com.ltm.be.dto.ExamLogDto;
import com.ltm.be.entity.ExamLogEntity;
import com.ltm.be.payload.request.webhook.ExamLogRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamLogRepository;
import com.ltm.be.repository.ExamRepository;
import com.ltm.be.repository.ExamUserRepository;
import com.ltm.be.service.IExamLogService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamLogServiceImpl extends BaseServiceImpl<ExamLogDto, ExamLogEntity, Long> implements IExamLogService {
    private final ExamLogRepository examLogRepository;
    private final ExamRepository examRepository;
    private final ExamUserRepository examUserRepository;

    public ExamLogServiceImpl(ExamLogRepository examLogRepository,
                              ExamLogConverter examLogConverter,
                              ExamRepository examRepository,
                              ExamUserRepository examUserRepository) {
        super(examLogRepository, examLogConverter);
        this.examLogRepository = examLogRepository;
        this.examRepository = examRepository;
        this.examUserRepository = examUserRepository;
    }

    @Override
    public ExamLogDto create(ExamLogRequest request) {
        ExamLogEntity clientLog = ExamLogEntity.builder()
                .exam(examRepository.getReferenceById(request.getExamId()))
                .examUser(examUserRepository.getReferenceById(request.getExamUserId()))
                .content(request.getMessage())
                .build();
        return create(clientLog);
    }

    @Override
    public PageResponse<?> getExamLogByUser(Long examId, Long userId, int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamLogEntity> clientLogs = examLogRepository.findAllByExamId(examId, pageable);
        return getPageByList(clientLogs, pageNo, pageSize);
    }

    @Override
    public void clearLogs() {
        examLogRepository.deleteAll();
    }
}
