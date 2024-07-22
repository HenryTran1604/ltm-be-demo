package com.ltm.be.service.impl;

import com.ltm.be.converter.UserExerciseContestConverter;
import com.ltm.be.entity.UserExerciseContestEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.UserExerciseContestRepository;
import com.ltm.be.service.IUserExerciseContestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UseExerciseContestServiceImpl implements IUserExerciseContestService {
    private final UserExerciseContestRepository userExerciseContestRepository;
    private final UserExerciseContestConverter userExerciseContestConverter;
    @Override
    public PageResponse<?> getExercisesAssignedToUser(Long userId, Long contestId, int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserExerciseContestEntity> userExerciseContestEntityPage = userExerciseContestRepository.findAllByUserContest_User_Id(userId, pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(userExerciseContestEntityPage.getTotalPages())
                .totalElements(userExerciseContestEntityPage.getTotalElements())
                .items(userExerciseContestEntityPage.stream().map(userExerciseContestConverter::toDto).toList())
                .build();
    }
}
