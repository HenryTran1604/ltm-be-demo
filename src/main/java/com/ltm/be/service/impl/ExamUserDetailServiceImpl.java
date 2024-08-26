package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamUserExerciseConverter;
import com.ltm.be.dto.ExamUserDetailDto;
import com.ltm.be.entity.ExamUserDetailEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamUserDetailRepository;
import com.ltm.be.service.IExamUserDetailService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamUserDetailServiceImpl extends BaseServiceImpl<ExamUserDetailDto, ExamUserDetailEntity> implements IExamUserDetailService {
    private final ExamUserDetailRepository examUserDetailRepository;
    public ExamUserDetailServiceImpl(ExamUserDetailRepository examUserDetailRepository,
                                     ExamUserExerciseConverter examUserExerciseConverter) {
        super(examUserDetailRepository, examUserExerciseConverter);
        this.examUserDetailRepository = examUserDetailRepository;
    }
    @Override
    public PageResponse<?> getExercisesAssignedToUser(Long userId, Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo -1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamUserDetailEntity> examUserExercises = examUserDetailRepository.findAllByExamUser_User_Id(userId, pageable);
        return getPageByList(examUserExercises, pageNo, pageSize);
    }
}
