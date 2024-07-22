package com.ltm.be.service.impl;

import com.ltm.be.converter.ContestConverter;
import com.ltm.be.converter.UserContestConverter;
import com.ltm.be.converter.UserConverter;
import com.ltm.be.dto.UserContestDto;
import com.ltm.be.entity.ContestEntity;
import com.ltm.be.entity.UserContestEntity;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.RegistrationContestRequest;
import com.ltm.be.payload.request.UserContestRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ContestRepository;
import com.ltm.be.repository.UserContestRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IUserContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserContestServiceImpl implements IUserContestService {
    private final ContestRepository contestRepository;
    private final ContestConverter contestConverter;
    private final UserContestConverter userContestConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final UserContestRepository userContestRepository;

    @Override
    public void addUsersToContest(UserContestRequest request) {
        ContestEntity contest = contestRepository.findById(request.getContestId()).orElseThrow(() -> new ResourceNotFoundException("Contest not exist!"));
        List<UserEntity> users = userRepository.findAllById(request.getUserIds());
        // check user must exist
        if (users.size() != request.getUserIds().size()) {
            Set<Long> userIds = users.stream().map(UserEntity::getId).collect(Collectors.toSet());
            Set<Long> nonExistingUserIds = request.getUserIds().stream()
                    .filter(id -> !userIds.contains(id))
                    .collect(Collectors.toSet());
            throw new ResourceNotFoundException("Users with IDs " + nonExistingUserIds + " do not exist!");
        }

        // check
        List<UserContestEntity> existingUserContests = userContestRepository.findAllByContestIdAndUserIdIn(request.getContestId(), request.getUserIds());

        if (!existingUserContests.isEmpty()) {
            Set<Long> existingUserIds = existingUserContests.stream()
                    .map(userContest -> userContest.getUser().getId())
                    .collect(Collectors.toSet());
            throw new DataConflictException("Users with IDs " + existingUserIds + " have already joined this contest!");
        }
        List<UserContestEntity> userContests = users.stream().map(
                user -> UserContestEntity.builder()
                        .contest(contest)
                        .user(user)
                        .build()
        ).toList();
        userContestRepository.saveAll(userContests);
    }

    @Override
    public void registerContest(RegistrationContestRequest request) {
        if(userContestRepository.existsByContestIdAndUserId(request.getContestId(), request.getUserId())) {
            throw new DataConflictException("User registered already!");
        }
        UserContestEntity userContest = UserContestEntity.builder()
                .user(userRepository.getReferenceById(request.getUserId()))
                .contest(contestRepository.getReferenceById(request.getContestId()))
                .build();
        userContestRepository.save(userContest);
    }

    @Override
    public PageResponse<?> getAllContestWithRegistration(Long userId, int pageNo, int pageSize) {
        int page = 0;
        if (pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ContestEntity> contests = contestRepository.findAll(pageable);
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exists"));
        List<UserContestDto> userContests = new ArrayList<>();
        for(ContestEntity contest : contests) {
            UserContestDto userContest = new UserContestDto();
            userContest.setContest(contestConverter.toDto(contest));
            userContest.setUser(userConverter.toDto(user));
            userContest.setRegistered(userContestRepository.existsByContestIdAndUserId(contest.getId(), userId));
            userContests.add(userContest);
        }
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(contests.getTotalPages())
                .totalElements(contests.getTotalElements())
                .items(userContests)
                .build();
    }

    @Override
    public PageResponse<?> getAllRegisteredUserByContestId(Long contestId, int pageNo, int pageSize) {
        int page = 0;
        if (pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserContestEntity> usersInContest = userContestRepository.findAllByContestId(contestId, pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(usersInContest.getTotalPages())
                .totalElements(usersInContest.getTotalElements())
                .items(usersInContest.stream().map(userContestConverter::toDto).toList())
                .build();
    }
}
