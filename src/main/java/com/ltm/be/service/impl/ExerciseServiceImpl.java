package com.ltm.be.service.impl;

import com.ltm.be.converter.ExerciseConverter;
import com.ltm.be.dto.QuestionDto;
import com.ltm.be.entity.QuestionEntity;
import com.ltm.be.entity.GroupEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.QuestionRequest;
import com.ltm.be.repository.QuestionRepository;
import com.ltm.be.repository.GroupRepository;
import com.ltm.be.service.IExerciseService;
import com.ltm.be.service.base.BaseServiceImpl;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ExerciseServiceImpl extends BaseServiceImpl<QuestionDto, QuestionEntity> implements IExerciseService {
    private final GroupRepository groupRepository;
    private final QuestionRepository questionRepository;

    public ExerciseServiceImpl(QuestionRepository questionRepository,
                               ExerciseConverter exerciseConverter,
                               GroupRepository groupRepository) {
        super(questionRepository, exerciseConverter);
        this.groupRepository = groupRepository;
        this.questionRepository = questionRepository;
    }

    @Override
    @Transactional
    public void create(QuestionRequest request) {
        // check topic exist
        GroupEntity topic = groupRepository.findById(request.getGroupId()).orElseThrow(() -> new ResourceNotFoundException("Topic not exist!"));

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
    public void update(UUID id, QuestionRequest request) {
        // Tìm topic theo ID
        GroupEntity topic = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new ResourceNotFoundException("Topic not exist"));

        // Tìm exercise theo ID
        QuestionEntity exercise = questionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Topic not exist"));

        // Cập nhật thông tin exercise
        exercise.setName(request.getName());
        exercise.setGroup(topic);
        exercise.setContent(request.getContent());
        update(exercise);
    }

}
