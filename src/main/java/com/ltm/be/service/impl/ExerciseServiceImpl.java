package com.ltm.be.service.impl;

import com.ltm.be.converter.ExerciseConverter;
import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseServiceImpl implements IExerciseService {
    @Autowired
    private ExerciseRepository exerciseRepository;
    @Autowired
    private ExerciseConverter exerciseConverter;
    @Override
    public List<ExerciseDto> getAllExercises() {
        List<ExerciseEntity> exercises = exerciseRepository.findAll();
        return exercises.stream().map(exerciseConverter::toDto).toList();
    }

    @Override
    public ExerciseDto getAllExerciseById(Long id) {
        Optional<ExerciseEntity> optional = exerciseRepository.findById(id);
        return optional.map(entity -> exerciseConverter.toDto(entity)).orElse(null);
    }

    @Override
    public ExerciseDto addExercise(ExerciseDto dto) {
        ExerciseEntity entity = exerciseConverter.toEntity(dto);
        ExerciseDto response = exerciseConverter.toDto(exerciseRepository.save(entity));
        return response;
    }
}
