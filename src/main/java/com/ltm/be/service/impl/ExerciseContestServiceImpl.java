package com.ltm.be.service.impl;

import com.ltm.be.converter.ExerciseContestConverter;
import com.ltm.be.entity.ContestEntity;
import com.ltm.be.entity.ExerciseContestEntity;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.entity.UserContestEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExerciseContestRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ContestRepository;
import com.ltm.be.repository.ExerciseContestRepository;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.service.IExerciseContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExerciseContestServiceImpl implements IExerciseContestService {
    private final ContestRepository contestRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseContestRepository exerciseContestRepository;
    private final ExerciseContestConverter exerciseContestConverter;

    @Override
    public void addExercisesToContest(ExerciseContestRequest request) {
        ContestEntity contest = contestRepository.findById(request.getContestId()).orElseThrow(() -> new ResourceNotFoundException("Contest not exist!"));
        List<ExerciseEntity> exercises = exerciseRepository.findAllById(request.getExerciseIds());
        if(request.getExerciseIds().size() != exercises.size()) {
            Set<Long> exerciseIds = exercises.stream().map(ExerciseEntity::getId).collect(Collectors.toSet());
            Set<Long> nonExistingExerciseIds = request.getExerciseIds()
                            .stream().filter(id -> !exerciseIds.contains(id))
                    .collect(Collectors.toSet());
            throw new ResourceNotFoundException("Exercises with IDs " + nonExistingExerciseIds + " do not exist!");
        }
        List<ExerciseContestEntity> existingExerciseContests = exerciseContestRepository.findAllByContestIdAndExerciseIdIn(request.getContestId(), request.getExerciseIds());

        if (!existingExerciseContests.isEmpty()) {
            Set<Long> existingExerciseIds = existingExerciseContests.stream()
                    .map(exerciseContest -> exerciseContest.getExercise().getId())
                    .collect(Collectors.toSet());
            throw new DataConflictException("Users with IDs " + existingExerciseIds + " have already added this contest!");
        }
        List<ExerciseContestEntity> exerciseContests = exercises.stream().map(
                exercise -> ExerciseContestEntity.builder()
                        .exercise(exercise)
                        .contest(contest)
                        .build()
        ).toList();
        exerciseContestRepository.saveAll(exerciseContests);
    }

    @Override
    public PageResponse<?> getAllAddedExercisesByContestId(Long contestId, int pageNo, int pageSize) {
        int page = 0;
        if (pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExerciseContestEntity> exercisesInContest = exerciseContestRepository.findAllByContestId(contestId, pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(exercisesInContest.getTotalPages())
                .totalElements(exercisesInContest.getTotalElements())
                .items(exercisesInContest.stream().map(exerciseContestConverter::toDto).toList())
                .build();
    }

}
