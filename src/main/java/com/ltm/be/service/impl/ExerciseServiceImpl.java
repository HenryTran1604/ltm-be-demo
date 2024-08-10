package com.ltm.be.service.impl;

import com.ltm.be.converter.AbstractBaseConverter;
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
import com.ltm.be.repository.BaseRepository;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.repository.TopicRepository;
import com.ltm.be.service.IExerciseService;
import com.ltm.be.service.base.BaseServiceImpl;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExerciseServiceImpl extends BaseServiceImpl<ExerciseDto, ExerciseEntity, Long> implements IExerciseService {
    private final TopicRepository topicRepository;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository,
                               ExerciseConverter exerciseConverter,
                               TopicRepository topicRepository) {
        super(exerciseRepository, exerciseConverter);
        this.topicRepository = topicRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @Transactional
    public void create(ExerciseRequest request) {
        // check topic exist
        TopicEntity topic = topicRepository.findById(request.getTopicId()).orElseThrow(() -> new ResourceNotFoundException("Topic not exist!"));

        // save exercise
        ExerciseEntity entity = ExerciseEntity.builder()
                .name(request.getName())
                .content(request.getContent())
                .topic(topic)
                .build();
        create(entity);
    }

    @Override
    @Transactional
    public void update(Long id, ExerciseRequest request) {
        // Tìm topic theo ID
        TopicEntity topic = topicRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic not exist"));

        // Tìm exercise theo ID
        ExerciseEntity exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not exist"));

        // Cập nhật thông tin exercise
        exercise.setName(request.getName());
        exercise.setTopic(topic);
        exercise.setContent(request.getContent());
        update(exercise);
    }

}
