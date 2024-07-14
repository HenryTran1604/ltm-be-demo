package com.ltm.be.service.impl;

import com.ltm.be.converter.UserExerciseConverter;
import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.dto.UserExerciseDto;
import com.ltm.be.entity.UserExerciseEntity;
import com.ltm.be.repository.UserExerciseRepository;
import com.ltm.be.service.IExerciseService;
import com.ltm.be.service.IUserExerciseService;
import com.ltm.be.service.ISubmissionService;
import com.ltm.be.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserExerciseServiceImpl implements IUserExerciseService {
    private final UserExerciseConverter userExerciseConverter;
    private final UserExerciseRepository userExerciseRepository;

    public List<UserExerciseDto> getUserExercisesByUserId(Long userId) {
        List<UserExerciseEntity> entities = userExerciseRepository.findAllByUser_Id(userId);
        return entities.stream().map(userExerciseConverter::toDto).toList();
    }


}
