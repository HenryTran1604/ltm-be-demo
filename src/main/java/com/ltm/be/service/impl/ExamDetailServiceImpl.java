package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamExerciseConverter;
import com.ltm.be.dto.ExamDetailDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.entity.ExamDetailEntity;
import com.ltm.be.entity.QuestionEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExamDetailRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamDetailRepository;
import com.ltm.be.repository.ExamRepository;
import com.ltm.be.repository.QuestionRepository;
import com.ltm.be.service.IExamDetailService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamDetailServiceImpl implements IExamDetailService {
    private final ExamRepository examRepository;
    private final QuestionRepository questionRepository;
    private final ExamDetailRepository examDetailRepository;

    @Override
    public void create(ExamDetailRequest request) {
        ExamEntity exam = examRepository.findById(request.getExamId()).orElseThrow(() -> new ResourceNotFoundException("exam not exist!"));
        List<QuestionEntity> exercises = questionRepository.findAllById(request.getExerciseIds());
        if(request.getExerciseIds().size() != exercises.size()) {
            Set<UUID> exerciseIds = exercises.stream().map(QuestionEntity::getId).collect(Collectors.toSet());
            Set<UUID> nonExistingExerciseIds = request.getExerciseIds()
                            .stream().filter(id -> !exerciseIds.contains(id))
                    .collect(Collectors.toSet());
            throw new ResourceNotFoundException("Exercises with IDs " + nonExistingExerciseIds + " do not exist!");
        }
        List<ExamDetailEntity> existingExamExercises = null; //examExerciseRepository.findAllByExamIdAndExerciseIdIn(request.getExamId(), request.getExerciseIds());

        if (!existingExamExercises.isEmpty()) {
            Set<UUID> existingExerciseIds = existingExamExercises.stream()
                    .map(examExercise -> examExercise.getQuestion().getId())
                    .collect(Collectors.toSet());
            throw new DataConflictException("Exercises with IDs " + existingExerciseIds + " have already added this exam!");
        }
        List<ExamDetailEntity> examExercises = exercises.stream().map(
                exercise -> ExamDetailEntity.builder()
                        .question(exercise)
//                        .exam(exam)
                        .build()
        ).toList();
        examDetailRepository.saveAll(examExercises);
    }

    @Override
    public PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamDetailEntity> exercisesInExam = null; //examExerciseRepository.findAllByExamId(examId, pageable);
        return getPageByList(exercisesInExam, pageNo, pageSize);
    }

}
