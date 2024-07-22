package com.ltm.be.service;

import com.ltm.be.payload.request.ExerciseContestRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IExerciseContestService {
    void addExercisesToContest(ExerciseContestRequest request);

    PageResponse<?> getAllAddedExercisesByContestId(Long contestId, int pageNo, int pageSize);
}
