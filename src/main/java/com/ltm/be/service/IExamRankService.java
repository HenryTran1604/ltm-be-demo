package com.ltm.be.service;

import com.ltm.be.dto.ExamRankDto;
import com.ltm.be.entity.ExamUserEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamRankService extends IBaseService<ExamRankDto, ExamUserEntity> {
    PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize);
    ExamRankDto getByExamIdAndUserId(Long userId, Long examId);
}
