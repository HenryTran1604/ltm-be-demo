package com.ltm.be.service;

import com.ltm.be.dto.ExamSubmissionDto;
import com.ltm.be.entity.ExamSubmissionEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamSubmissionService extends IBaseService<ExamSubmissionDto, ExamSubmissionEntity, Long> {
    PageResponse<?> getAllByUserIdAndExamId(Long id, int pageNo, int pageSize);
}
