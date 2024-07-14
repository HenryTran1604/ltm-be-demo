package com.ltm.be.service.impl;

import com.ltm.be.converter.ExerciseConverter;
import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExerciseRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.service.IExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public PageResponse<?> getAllExercises(int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);

        Page<ExerciseEntity> exercises = exerciseRepository.findAll(pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(exercises.getTotalPages())
                .totalElements(exercises.getTotalElements())
                .items(exercises.stream().map(exerciseConverter::toDto).toList())
                .build();
    }

    @Override
    public ExerciseDto getAllExerciseById(Long id) {
        Optional<ExerciseEntity> optional = exerciseRepository.findById(id);
        return optional.map(entity -> exerciseConverter.toDto(entity)).orElseThrow(() -> new ResourceNotFoundException("Exercise not exist"));
    }

    @Override
    public void addExercise(ExerciseRequest request) {
        ExerciseEntity entity = ExerciseEntity.builder()
                .name(request.getName())
                .path(request.getPath())
                .build();
        exerciseRepository.save(entity);
    }
}
