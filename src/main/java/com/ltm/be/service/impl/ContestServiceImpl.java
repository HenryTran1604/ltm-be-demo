package com.ltm.be.service.impl;

import com.ltm.be.converter.ContestConverter;
import com.ltm.be.dto.ContestDto;
import com.ltm.be.entity.*;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ContestRequest;
import com.ltm.be.payload.request.RegistrationContestRequest;
import com.ltm.be.repository.*;
import com.ltm.be.service.IContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class ContestServiceImpl implements IContestService {
    private final ContestRepository contestRepository;
    private final UserContestRepository userContestRepository;
    private final ExerciseContestRepository exerciseContestRepository;
    private final TopicRepository topicRepository;
    private final UserExerciseContestRepository userExerciseContestRepository;
    private final ContestConverter contestConverter;
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
        List<UserContestEntity> users = userContestRepository.findAllByContestId(id);
        List<TopicEntity> topics = topicRepository.findAll();
        Random random = new Random();
        for (UserContestEntity user : users) {
            for (TopicEntity topic : topics) {
                List<ExerciseContestEntity> exercises = exerciseContestRepository.findByExercise_TopicId(topic.getId());
                if (!exercises.isEmpty()) {
                    int randomExerciseIndex = random.nextInt(exercises.size());
                    ExerciseContestEntity randomExercise = exercises.get(randomExerciseIndex);
                    List<AliasEntity> aliases = randomExercise.getExercise().getAliases();
                    if(!aliases.isEmpty()) {
                        int randomExerciseAlias = random.nextInt(aliases.size());
                        System.out.println(aliases + " " + randomExerciseAlias);
                        UserExerciseContestEntity userExercise = UserExerciseContestEntity.builder()
                                .exerciseContest(randomExercise)
                                .userContest(user)
                                .ac(false)
                                .srcPath("")
                                .alias(aliases.get(randomExerciseAlias))
                                .build();
                        userExerciseContestRepository.save(userExercise);
                    } else {
                        throw new DataConflictException("Some exercises have no alias");
                    }
                }
            }
        }
    }

}
