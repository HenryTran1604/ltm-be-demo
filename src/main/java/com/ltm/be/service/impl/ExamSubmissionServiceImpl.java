package com.ltm.be.service.impl;

import com.ltm.be.converter.AbstractBaseConverter;
import com.ltm.be.converter.ExamSubmissionConverter;
import com.ltm.be.dto.ExamSubmissionDto;
import com.ltm.be.entity.ExamSubmissionEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.BaseRepository;
import com.ltm.be.repository.ExamSubmissionRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IExamSubmissionService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamSubmissionServiceImpl extends BaseServiceImpl<ExamSubmissionDto, ExamSubmissionEntity, Long> implements IExamSubmissionService {

    public ExamSubmissionServiceImpl(ExamSubmissionRepository examSubmissionRepository, UserRepository userRepository, ExamSubmissionConverter examSubmissionConverter) {
        super(examSubmissionRepository, examSubmissionConverter);
    }

    @Override
    public PageResponse<?> getAllByUserIdAndExamId(Long id, int pageNo, int pageSize) {
        return null;
    }

}
