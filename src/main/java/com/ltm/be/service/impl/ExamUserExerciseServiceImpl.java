package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamUserExerciseConverter;
import com.ltm.be.dto.ExamUserExerciseDto;
import com.ltm.be.entity.ExamUserExerciseEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamUserExerciseRepository;
import com.ltm.be.service.IExamUserExerciseService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ExamUserExerciseServiceImpl extends BaseServiceImpl<ExamUserExerciseDto, ExamUserExerciseEntity, Long> implements IExamUserExerciseService {
    private final ExamUserExerciseRepository examUserExerciseRepository;
    public ExamUserExerciseServiceImpl(ExamUserExerciseRepository examUserExerciseRepository,
                                       ExamUserExerciseConverter examUserExerciseConverter) {
        super(examUserExerciseRepository, examUserExerciseConverter);
        this.examUserExerciseRepository = examUserExerciseRepository;
    }
    @Override
    public PageResponse<?> getExercisesAssignedToUser(Long userId, Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo -1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamUserExerciseEntity> examUserExercises = examUserExerciseRepository.findAllByExamUser_User_Id(userId, pageable);
        return getPageByList(examUserExercises, pageNo, pageSize);
    }
}
