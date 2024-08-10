package com.ltm.be.service;

import com.ltm.be.dto.ExamDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.payload.request.ExamRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;


import java.util.List;

public interface IExamService extends IBaseService<ExamDto, ExamEntity, Long> {
    void create(ExamRequest request);
    void update(Long id, ExamRequest request);
    void assignExercisesToUsers(Long id);
}
