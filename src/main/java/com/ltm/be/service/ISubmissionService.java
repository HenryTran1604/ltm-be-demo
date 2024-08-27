package com.ltm.be.service;

import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface ISubmissionService {
    PageResponse<?> getAllByUserIdAndExamId(Long id, int pageNo, int pageSize);
}
