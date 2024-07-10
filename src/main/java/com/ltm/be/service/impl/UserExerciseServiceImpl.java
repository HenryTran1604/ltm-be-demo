package com.ltm.be.service.impl;

import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.service.IExerciseService;
import com.ltm.be.service.IUserExerciseService;
import com.ltm.be.service.ISubmissionService;
import com.ltm.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserExerciseServiceImpl implements IUserExerciseService {
    @Autowired
    private ISubmissionService submissionService;
    @Autowired
    private IExerciseService exerciseService;
    @Autowired
    private IUserService userService;

    public List<UserExerciseDto> getUserExercisesByUserId(Long userId) {
        List<ExerciseDto> exerciseDtos = exerciseService.getAllExercises();
        List<UserExerciseDto> userExerciseDtos = new ArrayList<>();
        for(ExerciseDto exerciseDto : exerciseDtos) {
            UserExerciseDto userExerciseDto = new UserExerciseDto();
            userExerciseDto.setUserId(userId);
            userExerciseDto.setExerciseId(exerciseDto.getId());
            userExerciseDto.setAC(submissionService.existsByUserIdAndExerciseIdAndAc(userId, exerciseDto.getId(), 1) ? 1 : 0);
            userExerciseDto.setAttemptCount(submissionService.countByUserIdAndExerciseId(userId, exerciseDto.getId()));
            userExerciseDtos.add(userExerciseDto);
        }
        return userExerciseDtos;
    }


}
