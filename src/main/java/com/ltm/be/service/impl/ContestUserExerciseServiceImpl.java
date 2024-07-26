package com.ltm.be.service.impl;

import com.ltm.be.converter.ContestUserExerciseConverter;
import com.ltm.be.entity.ContestUserExerciseEntity;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ContestUserExerciseRepository;
import com.ltm.be.service.IContestUserExerciseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestUserExerciseServiceImpl implements IContestUserExerciseService {
    private final ContestUserExerciseRepository contestUserExerciseRepository;
    private final ContestUserExerciseConverter contestUserExerciseConverter;
    @Override
    public PageResponse<?> getExercisesAssignedToUser(Long userId, Long contestId, int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ContestUserExerciseEntity> contestUserExercises = contestUserExerciseRepository.findAllByContestUser_User_Id(userId, pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(contestUserExercises.getTotalPages())
                .totalElements(contestUserExercises.getTotalElements())
                .items(contestUserExercises.stream().map(contestUserExerciseConverter::toDto).toList())
                .build();
    }
}
