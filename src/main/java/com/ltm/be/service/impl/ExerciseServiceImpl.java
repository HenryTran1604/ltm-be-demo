package com.ltm.be.service.impl;

import com.ltm.be.converter.ExerciseConverter;
import com.ltm.be.dto.QuestionDto;
import com.ltm.be.entity.QuestionEntity;
import com.ltm.be.entity.GroupEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExerciseRequest;
import com.ltm.be.repository.ExerciseRepository;
import com.ltm.be.repository.GroupRepository;
import com.ltm.be.service.IExerciseService;
import com.ltm.be.service.base.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ExerciseServiceImpl extends BaseServiceImpl<QuestionDto, QuestionEntity> implements IExerciseService {
    private final GroupRepository groupRepository;
    private final ExerciseRepository exerciseRepository;

    public ExerciseServiceImpl(ExerciseRepository exerciseRepository,
                               ExerciseConverter exerciseConverter,
                               GroupRepository groupRepository) {
        super(exerciseRepository, exerciseConverter);
        this.groupRepository = groupRepository;
        this.exerciseRepository = exerciseRepository;
    }

    @Override
    @Transactional
    public void create(ExerciseRequest request) {
        // check topic exist
        GroupEntity topic = groupRepository.findById(request.getTopicId()).orElseThrow(() -> new ResourceNotFoundException("Topic not exist!"));

        // save exercise
        QuestionEntity entity = QuestionEntity.builder()
                .name(request.getName())
                .content(request.getContent())
                .group(topic)
                .build();
        create(entity);
    }

    @Override
    @Transactional
    public void update(Long id, ExerciseRequest request) {
        // Tìm topic theo ID
        GroupEntity topic = groupRepository.findById(request.getTopicId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic not exist"));

        // Tìm exercise theo ID
        QuestionEntity exercise = exerciseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not exist"));

        // Cập nhật thông tin exercise
        exercise.setName(request.getName());
        exercise.setGroup(topic);
        exercise.setContent(request.getContent());
        update(exercise);
    }

}
