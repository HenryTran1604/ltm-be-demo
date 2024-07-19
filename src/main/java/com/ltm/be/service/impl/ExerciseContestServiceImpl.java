package com.ltm.be.service.impl;

import com.ltm.be.entity.ContestEntity;
import com.ltm.be.entity.ExerciseContestEntity;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExerciseContestRequest;
import com.ltm.be.repository.ContestRepository;
import com.ltm.be.repository.ExerciseContestRepository;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.service.IExerciseContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseContestServiceImpl implements IExerciseContestService {
    private final ContestRepository contestRepository;
    private final ExerciseRepository exerciseRepository;
    private final ExerciseContestRepository exerciseContestRepository;

    @Override
    public void addExercisesToContest(ExerciseContestRequest request) {
        ContestEntity contest = contestRepository.findById(request.getContestId()).orElseThrow(() -> new ResourceNotFoundException("Contest not exist!"));
        List<ExerciseEntity> exercises = exerciseRepository.findAllById(request.getExerciseIds());
        for (ExerciseEntity exercise : exercises) {
            exerciseContestRepository.save(ExerciseContestEntity.builder()
                    .contest(contest)
                    .exercise(exercise)
                    .build());
        }
    }
}
