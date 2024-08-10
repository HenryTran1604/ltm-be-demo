package com.ltm.be.service.impl;

import com.ltm.be.converter.AbstractBaseConverter;
import com.ltm.be.converter.ExamExerciseConverter;
import com.ltm.be.dto.ExamExerciseDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.entity.ExamExerciseEntity;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExamExerciseRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.BaseRepository;
import com.ltm.be.repository.ExamExerciseRepository;
import com.ltm.be.repository.ExamRepository;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.service.IExamExerciseService;
import com.ltm.be.service.base.BaseServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamExerciseServiceImpl extends BaseServiceImpl<ExamExerciseDto, ExamExerciseEntity, Long> implements IExamExerciseService {
    private final ExamRepository examRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExamExerciseRepository examExerciseRepository;

    public ExamExerciseServiceImpl(
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
        List<ExerciseEntity> exercises = exerciseRepository.findAllById(request.getExerciseIds());
        if(request.getExerciseIds().size() != exercises.size()) {
            Set<Long> exerciseIds = exercises.stream().map(ExerciseEntity::getId).collect(Collectors.toSet());
            Set<Long> nonExistingExerciseIds = request.getExerciseIds()
                            .stream().filter(id -> !exerciseIds.contains(id))
                    .collect(Collectors.toSet());
            throw new ResourceNotFoundException("Exercises with IDs " + nonExistingExerciseIds + " do not exist!");
        }
        List<ExamExerciseEntity> existingExamExercises = examExerciseRepository.findAllByExamIdAndExerciseIdIn(request.getExamId(), request.getExerciseIds());

        if (!existingExamExercises.isEmpty()) {
            Set<Long> existingExerciseIds = existingExamExercises.stream()
                    .map(examExercise -> examExercise.getExercise().getId())
                    .collect(Collectors.toSet());
            throw new DataConflictException("Exercises with IDs " + existingExerciseIds + " have already added this exam!");
        }
        List<ExamExerciseEntity> examExercises = exercises.stream().map(
                exercise -> ExamExerciseEntity.builder()
                        .exercise(exercise)
                        .exam(exam)
                        .build()
        ).toList();
        examExerciseRepository.saveAll(examExercises);
    }

    @Override
    public PageResponse<?> getAllByExamId(Long examId, int pageNo, int pageSize) {
        int page = 0;
        if (pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamExerciseEntity> exercisesInExam = examExerciseRepository.findAllByExamId(examId, pageable);
        return getPageByList(exercisesInExam, pageNo, pageSize);
    }

}
