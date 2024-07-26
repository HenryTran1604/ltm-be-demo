package com.ltm.be.service;

import com.ltm.be.payload.request.ContestExerciseRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IContestExerciseService {
    void addExercisesToContest(ContestExerciseRequest request);

    PageResponse<?> getAllAddedExercisesByContestId(Long contestId, int pageNo, int pageSize);
}
