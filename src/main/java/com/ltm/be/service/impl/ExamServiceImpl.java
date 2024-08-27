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
public class ExamServiceImpl extends BaseServiceImpl<ExamDto, ExamEntity> implements IExamService {
    private final ExamUserRepository examUserRepository;
    private final ExamDetailRepository examDetailRepository;
    private final GroupRepository groupRepository;
    private final ExamUserDetailRepository examUserDetailRepository;
    private final AliasRepository aliasRepository;
    private final IAliasService aliasService;

    public ExamServiceImpl(ExamRepository examRepository,
                           ExamUserRepository examUserRepository,
                           ExamDetailRepository examDetailRepository,
                           GroupRepository groupRepository,
                           ExamUserDetailRepository examUserDetailRepository,
                           ExamConverter examConverter,
                           AliasRepository aliasRepository,
                           IAliasService aliasService) {
        super(examRepository, examConverter);
        this.examUserRepository = examUserRepository;
        this.examDetailRepository = examDetailRepository;
        this.groupRepository = groupRepository;
        this.examUserDetailRepository = examUserDetailRepository;
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
        List<GroupEntity> topics = groupRepository.findAll();
        Random random = new Random();
        for (ExamUserEntity user : users) {
            for (GroupEntity topic : topics) {
                List<ExamDetailEntity> exercises = examDetailRepository.findByExercise_TopicId(topic.getId());
                if (!exercises.isEmpty()) {
                    int randomExerciseIndex = random.nextInt(exercises.size());
                    ExamDetailEntity randomExercise = exercises.get(randomExerciseIndex);
                    String aliasCode = aliasService.generateAliasCode(7);
                    AliasEntity alias = AliasEntity.builder()
                            .code(aliasCode)
                            .build();
                    aliasRepository.save(alias);

                    ExamUserDetailEntity userExercise = ExamUserDetailEntity.builder()
                            .examDetail(randomExercise)
                            .examUser(user)
                            .ac(false)
                            .alias(alias)
                            .srcPath("")
                            .build();
                    examUserDetailRepository.save(userExercise);
                }
            }
        }
    }
}


