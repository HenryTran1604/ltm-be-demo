package com.ltm.be.service;

import com.ltm.be.payload.response.PageResponse;

public interface IContestUserExerciseService {
    PageResponse<?> getExercisesAssignedToUser(Long userId, Long contestId, int pageNo, int pageSize);
}
