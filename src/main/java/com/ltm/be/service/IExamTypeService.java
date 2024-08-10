package com.ltm.be.service;

import com.ltm.be.dto.ExamTypeDto;
import com.ltm.be.entity.ExamTypeEntity;
import com.ltm.be.payload.request.ExamTypeRequest;
import com.ltm.be.service.base.IBaseService;

public interface IExamTypeService extends IBaseService<ExamTypeDto, ExamTypeEntity, Integer> {
    void create(ExamTypeRequest request);
}
