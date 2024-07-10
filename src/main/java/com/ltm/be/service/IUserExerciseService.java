package com.ltm.be.service;

import com.ltm.be.dto.UserExerciseDto;

import java.util.List;

public interface IUserExerciseService {
    List<UserExerciseDto> getUserExercisesByUserId(Long userId);
}
