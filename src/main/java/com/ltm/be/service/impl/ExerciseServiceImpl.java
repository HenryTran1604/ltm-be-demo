package com.ltm.be.service.impl;

import com.ltm.be.converter.ExerciseConverter;
import com.ltm.be.dto.ExerciseDto;
import com.ltm.be.entity.AliasEntity;
import com.ltm.be.entity.ExerciseEntity;
import com.ltm.be.entity.TopicEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.AliasRequest;
import com.ltm.be.payload.request.ExerciseRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.AliasRepository;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.repository.TopicRepository;
import com.ltm.be.service.IExerciseService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExerciseServiceImpl implements IExerciseService {
    private final ExerciseRepository exerciseRepository;
    private final ExerciseConverter exerciseConverter;
    private final TopicRepository topicRepository;

    @Override
    public PageResponse<?> getAllExercises(int pageNo, int pageSize) {
        int page = 0;
        if (pageNo > 0) {
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
        return optional.map(exerciseConverter::toDto).orElseThrow(() -> new ResourceNotFoundException("Exercise not exist"));
    }

    @Override
    @Transactional
    public void addExercise(ExerciseRequest request) {
        // check topic exist
        TopicEntity topic = topicRepository.findById(request.getTopicId()).orElseThrow(() -> new ResourceNotFoundException("Topic not exist!"));

        // save exercise
        ExerciseEntity entity = ExerciseEntity.builder()
                .name(request.getName())
                .content(request.getContent())
                .topic(topic)
                .build();
        ExerciseEntity result = exerciseRepository.save(entity);
    }

    @Override
    @Transactional
    public void updateExercise(Long id, ExerciseRequest request) {
        // Tìm topic theo ID
        TopicEntity topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic not exist!"));

        // Tìm exercise theo ID
        ExerciseEntity exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Exercise not exist"));

        // Cập nhật thông tin exercise
        exercise.setName(request.getName());
        exercise.setTopic(topic);
        exercise.setContent(request.getContent());
        exerciseRepository.save(exercise);
    }

}
