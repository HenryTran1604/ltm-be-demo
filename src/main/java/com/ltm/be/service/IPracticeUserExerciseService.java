package com.ltm.be.service;

import com.ltm.be.payload.response.PageResponse;

public interface IPracticeUserExerciseService {
    void addUserToPractice(Long userId);
    PageResponse<?> getAllPracticeUserExerciseByUser(Long userId, int pageNo, int pageSize);
}
