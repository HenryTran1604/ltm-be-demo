package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamConverter;
import com.ltm.be.dto.ExamDto;
import com.ltm.be.entity.*;
import com.ltm.be.payload.request.ExamRequest;
import com.ltm.be.repository.*;
import com.ltm.be.service.IAliasService;
import com.ltm.be.service.IExamService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class ExamServiceImpl extends BaseServiceImpl<ExamDto, ExamEntity, Long> implements IExamService {
    private final ExamUserRepository examUserRepository;
    private final ExamExerciseRepository examExerciseRepository;
    private final TopicRepository topicRepository;
    private final ExamUserExerciseRepository examUserExerciseRepository;
    private final AliasRepository aliasRepository;
    private final IAliasService aliasService;

    public ExamServiceImpl(ExamRepository examRepository,
                           ExamUserRepository examUserRepository,
                           ExamExerciseRepository examExerciseRepository,
                           TopicRepository topicRepository,
                           ExamUserExerciseRepository examUserExerciseRepository,
                           ExamConverter examConverter,
                           AliasRepository aliasRepository,
                           IAliasService aliasService) {
        super(examRepository, examConverter);
        this.examUserRepository = examUserRepository;
        this.examExerciseRepository = examExerciseRepository;
        this.topicRepository = topicRepository;
        this.examUserExerciseRepository = examUserExerciseRepository;
        this.aliasRepository = aliasRepository;
        this.aliasService = aliasService;
    }

    @Override
    public void create(ExamRequest request) {
        ExamEntity exam = ExamEntity.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .title(request.getTitle())
                .build();
        create(exam);
    }

    @Override
    public void update(Long id, ExamRequest request) {
        ExamEntity exam = ExamEntity.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .title(request.getTitle())
                .build();
        exam.setId(id);
        update(exam);
    }

    @Override
    public void assignExercisesToUsers(Long id) {
        List<ExamUserEntity> users = examUserRepository.findAllByExamId(id);
        List<TopicEntity> topics = topicRepository.findAll();
        Random random = new Random();
        for (ExamUserEntity user : users) {
            for (TopicEntity topic : topics) {
                List<ExamExerciseEntity> exercises = examExerciseRepository.findByExercise_TopicId(topic.getId());
                if (!exercises.isEmpty()) {
                    int randomExerciseIndex = random.nextInt(exercises.size());
                    ExamExerciseEntity randomExercise = exercises.get(randomExerciseIndex);
                    String aliasCode = aliasService.generateAliasCode(7);
                    AliasEntity alias = AliasEntity.builder()
                            .code(aliasCode)
                            .exercise(randomExercise.getExercise())
                            .build();
                    aliasRepository.save(alias);

                    ExamUserExerciseEntity userExercise = ExamUserExerciseEntity.builder()
                            .examExercise(randomExercise)
                            .examUser(user)
                            .ac(false)
                            .alias(alias)
                            .srcPath("")
                            .build();

                    examUserExerciseRepository.save(userExercise);
                }
            }
        }
    }
}


