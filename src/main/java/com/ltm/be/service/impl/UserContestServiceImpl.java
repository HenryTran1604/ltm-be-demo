package com.ltm.be.service.impl;

import com.ltm.be.entity.ContestEntity;
import com.ltm.be.entity.UserContestEntity;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.UserContestRequest;
import com.ltm.be.repository.ContestRepository;
import com.ltm.be.repository.UserContestRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IUserContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserContestServiceImpl implements IUserContestService {
    private final ContestRepository contestRepository;
    private final UserRepository userRepository;
    private final UserContestRepository userContestRepository;

    @Override
    public void addUsersToContest(UserContestRequest request) {
        ContestEntity contest = contestRepository.findById(request.getContestId()).orElseThrow(() -> new ResourceNotFoundException("Contest not exist!"));
        List<UserEntity> users = userRepository.findAllById(request.getUserIds());
        for (UserEntity user : users) {
            userContestRepository.save(UserContestEntity.builder()
                    .contest(contest)
                    .user(user)
                    .build());
        }
    }
}
