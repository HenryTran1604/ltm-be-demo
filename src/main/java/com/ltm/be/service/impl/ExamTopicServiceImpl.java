package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamTopicConverter;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.entity.ExamTopicEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamRepository;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamTopicServiceImpl extends BaseServiceImpl<ExamTopicDto, ExamTopicEntity> implements IExamTopicService {
    private final ExamRepository examRepository;
    private final ExamTopicRepository examTopicRepository;
    public ExamTopicServiceImpl(ExamTopicRepository examTopicRepository,
                                ExamTopicConverter examTopicConverter,
                                ExamRepository examRepository) {
        super(examTopicRepository, examTopicConverter);
        this.examTopicRepository = examTopicRepository;
        this.examRepository = examRepository;
    }

    @Override
    public ExamTopicDto create(ExamTopicRequest request) {
        ExamEntity exam = examRepository.findById(request.getExamId()).orElseThrow(() -> new ResourceNotFoundException("Exam not exists"));
        ExamTopicEntity entity = ExamTopicEntity.builder()
                .name(request.getName())
                .exam(exam)
                .build();
        return create(entity);
    }

    @Override
    public ExamTopicDto update(Long id, ExamTopicRequest request) {
        ExamTopicEntity examTopic = examTopicRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Exam topic not exists"));
        ExamEntity exam = examRepository.findById(request.getExamId()).orElseThrow(() -> new ResourceNotFoundException("Exam not exists"));
        examTopic.setName(request.getName());
        examTopic.setExam(exam);
        return update(examTopic);
    }

    @Override
    public PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamTopicEntity> examTopics = examTopicRepository.findAllByExamId(examId, pageable);
        return getPageByList(examTopics, pageNo, pageSize);
    }
}
