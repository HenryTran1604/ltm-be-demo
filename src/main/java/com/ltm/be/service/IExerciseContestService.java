package com.ltm.be.service;

import com.ltm.be.payload.request.ExerciseContestRequest;

public interface IExerciseContestService {
    void addExercisesToContest(ExerciseContestRequest request);
}
