package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamSubmissionConverter;
import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.SubmissionRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.ISubmissionService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class SubmissionServiceImpl extends BaseServiceImpl<SubmissionDto, SubmissionEntity> implements ISubmissionService {

    public SubmissionServiceImpl(SubmissionRepository submissionRepository, UserRepository userRepository, ExamSubmissionConverter examSubmissionConverter) {
        super(submissionRepository, examSubmissionConverter);
    }

    @Override
    public PageResponse<?> getAllByUserIdAndExamId(Long id, int pageNo, int pageSize) {
        return null;
    }

}
