package com.ltm.be.service.impl;

import com.ltm.be.converter.ContestConverter;
import com.ltm.be.dto.ContestDto;
import com.ltm.be.entity.*;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ContestRequest;
import com.ltm.be.repository.*;
import com.ltm.be.service.IAliasService;
import com.ltm.be.service.IContestService;
import com.ltm.be.service.IRandomGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements IContestService {
    private final ContestRepository contestRepository;
    private final ContestUserRepository contestUserRepository;
    private final ContestExerciseRepository contestExerciseRepository;
    private final TopicRepository topicRepository;
    private final ContestUserExerciseRepository contestUserExerciseRepository;
    private final ContestConverter contestConverter;
    private final AliasRepository aliasRepository;
    private final IAliasService aliasService;

    @Override
    public void create(ContestRequest request) {
        ContestEntity contest = ContestEntity.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .title(request.getTitle())
                .build();
        contestRepository.save(contest);
    }

    @Override
    public void update(Long id, ContestRequest request) {
        ContestEntity contest = ContestEntity.builder()
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .title(request.getTitle())
                .build();
        contest.setId(id);
        contestRepository.save(contest);
    }

    @Override
    public List<ContestDto> getAllContest() {
        return contestRepository.findAll().stream().map(contestConverter::toDto).toList();
    }

    @Override
    public ContestDto getContestById(Long id) {
        ContestEntity entity = contestRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Contest not exist!"));
        return contestConverter.toDto(entity);
    }

    @Override
    public void assignExercisesToUsers(Long id) {
        List<ContestUserEntity> users = contestUserRepository.findAllByContestId(id);
        List<TopicEntity> topics = topicRepository.findAll();
        Random random = new Random();
        for (ContestUserEntity user : users) {
            for (TopicEntity topic : topics) {
                List<ContestExerciseEntity> exercises = contestExerciseRepository.findByExercise_TopicId(topic.getId());
                if (!exercises.isEmpty()) {
                    int randomExerciseIndex = random.nextInt(exercises.size());
                    ContestExerciseEntity randomExercise = exercises.get(randomExerciseIndex);
                    String aliasCode = aliasService.generateAliasCode(7);
                    AliasEntity alias = AliasEntity.builder()
                            .code(aliasCode)
                            .exercise(randomExercise.getExercise())
                            .build();
                    aliasRepository.save(alias);

                    ContestUserExerciseEntity userExercise = ContestUserExerciseEntity.builder()
                            .contestExercise(randomExercise)
                            .contestUser(user)
                            .ac(false)
                            .alias(alias)
                            .srcPath("")
                            .build();

                    contestUserExerciseRepository.save(userExercise);
                }
            }
        }
    }
}


