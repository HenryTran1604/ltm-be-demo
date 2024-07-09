package com.ltm.be.service.impl;

import com.ltm.be.converter.SubmissionConverter;
import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.dto.SubmissionDto;
import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.repository.SubmissionRepository;
import com.ltm.be.service.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class SubmissionServiceImpl implements ISubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private SubmissionConverter submissionConverter;

    @Override
    public List<SubmissionDto> getAllByUserId(Long userId) {
        List<SubmissionEntity> entities = submissionRepository.findAllByUser_Id(userId);
        return entities.stream().map(submissionConverter::toDto).toList();
    }

    @Override
    public void updateByUserAndExercise(Long userId, Long exerciseId, LocalDate submittedAt, Integer ac, String srcPath) {
        submissionRepository.addByUserIdAndExerciseId(userId, exerciseId, submittedAt, ac, srcPath);
    }

    @Override
    public Integer countByUserIdAndExerciseId(Long userId, Long exerciseId) {
        return submissionRepository.countByUser_IdAndExercise_Id(userId, exerciseId);
    }

    @Override
    public boolean existsByUserIdAndExerciseIdAndAc(Long userId, Long exerciseId, Integer ac) {
        return submissionRepository.existsByUserIdAndExerciseIdAndAc(userId, exerciseId, ac);
    }


}
