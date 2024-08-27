package com.ltm.be.service;

import com.ltm.be.dto.ExamUserDetailDto;
import com.ltm.be.entity.ExamUserDetailEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IExamUserDetailService {
    PageResponse<?> getExercisesAssignedToUser(Long userId, Long examId, int pageNo, int pageSize);
}
