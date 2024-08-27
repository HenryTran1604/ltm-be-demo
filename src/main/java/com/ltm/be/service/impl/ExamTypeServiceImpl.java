package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamTypeConverter;
import com.ltm.be.dto.ExamTypeDto;
import com.ltm.be.entity.ExamTypeEntity;
import com.ltm.be.payload.request.ExamTypeRequest;
import com.ltm.be.repository.ExamTypeRepository;
import com.ltm.be.service.IExamTypeService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExamTypeServiceImpl implements IExamTypeService {
    private final ExamTypeRepository examTypeRepository;
    private final ExamTypeConverter examTypeConverter;
    @Override
    public ExamTypeDto create(ExamTypeRequest request) {
        ExamTypeEntity entity = ExamTypeEntity.builder()
                .name(request.getName())
                .build();
        return examTypeConverter.toDto(examTypeRepository.save(entity));
    }
}
