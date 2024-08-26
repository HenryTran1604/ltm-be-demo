package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamExerciseConverter;
import com.ltm.be.dto.ExamDetailDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.entity.ExamDetailEntity;
import com.ltm.be.entity.QuestionEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExamExerciseRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamExerciseRepository;
import com.ltm.be.repository.ExamRepository;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.service.IExamDetailService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamDetailServiceImpl extends BaseServiceImpl<ExamDetailDto, ExamDetailEntity> implements IExamDetailService {
    private final ExamRepository examRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExamExerciseRepository examExerciseRepository;

    public ExamDetailServiceImpl(
            ExamRepository examRepository,
            ExerciseRepository exerciseRepository,
            ExamExerciseRepository examExerciseRepository,
            ExamExerciseConverter exerciseConverter
    ) {
        super(examExerciseRepository, exerciseConverter);
        this.examRepository = examRepository;
        this.exerciseRepository = exerciseRepository;
        this.examExerciseRepository = examExerciseRepository;
    }

    @Override
    public void addExercisesToExam(ExamExerciseRequest request) {
        ExamEntity exam = examRepository.findById(request.getExamId()).orElseThrow(() -> new ResourceNotFoundException("exam not exist!"));
        List<QuestionEntity> exercises = exerciseRepository.findAllById(request.getExerciseIds());
        if(request.getExerciseIds().size() != exercises.size()) {
            Set<Long> exerciseIds = exercises.stream().map(QuestionEntity::getId).collect(Collectors.toSet());
            Set<Long> nonExistingExerciseIds = request.getExerciseIds()
                            .stream().filter(id -> !exerciseIds.contains(id))
                    .collect(Collectors.toSet());
            throw new ResourceNotFoundException("Exercises with IDs " + nonExistingExerciseIds + " do not exist!");
        }
        List<ExamDetailEntity> existingExamExercises = null; //examExerciseRepository.findAllByExamIdAndExerciseIdIn(request.getExamId(), request.getExerciseIds());

        if (!existingExamExercises.isEmpty()) {
            Set<Long> existingExerciseIds = existingExamExercises.stream()
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
        examExerciseRepository.saveAll(examExercises);
    }

    @Override
    public PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamDetailEntity> exercisesInExam = null; //examExerciseRepository.findAllByExamId(examId, pageable);
        return getPageByList(exercisesInExam, pageNo, pageSize);
    }

}
