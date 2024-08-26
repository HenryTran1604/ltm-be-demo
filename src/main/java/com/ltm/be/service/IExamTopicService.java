package com.ltm.be.service;

import com.ltm.be.dto.ExamTopicDto;
import com.ltm.be.entity.ExamTopicEntity;
import com.ltm.be.payload.request.ExamTopicRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamTopicService extends IBaseService<ExamTopicDto, ExamTopicEntity> {
    ExamTopicDto create(ExamTopicRequest request);
    ExamTopicDto update(Long id, ExamTopicRequest request);
    PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize);
}
