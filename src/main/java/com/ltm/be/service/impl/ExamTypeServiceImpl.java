package com.ltm.be.service.impl;

import com.ltm.be.converter.AbstractBaseConverter;
import com.ltm.be.converter.ExamTypeConverter;
import com.ltm.be.dto.ExamTypeDto;
import com.ltm.be.entity.ExamTypeEntity;
import com.ltm.be.payload.request.ExamTypeRequest;
import com.ltm.be.repository.BaseRepository;
import com.ltm.be.repository.ExamTypeRepository;
import com.ltm.be.service.IExamTypeService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class ExamTypeServiceImpl extends BaseServiceImpl<ExamTypeDto, ExamTypeEntity, Integer> implements IExamTypeService {
    public ExamTypeServiceImpl(ExamTypeRepository examTypeRepository, ExamTypeConverter examTypeConverter) {
        super(examTypeRepository, examTypeConverter);
    }

    @Override
    public void create(ExamTypeRequest request) {
        ExamTypeEntity examType = ExamTypeEntity.builder()
                .name(request.getName())
                .build();
        create(examType);
    }
}
