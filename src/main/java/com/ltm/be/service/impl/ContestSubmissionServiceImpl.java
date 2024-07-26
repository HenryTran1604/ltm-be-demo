package com.ltm.be.service.impl;

import com.ltm.be.converter.ContestSubmissionConverter;
import com.ltm.be.entity.ContestSubmissionEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ContestSubmissionRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IContestSubmissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContestSubmissionServiceImpl implements IContestSubmissionService {
    private final ContestSubmissionRepository contestSubmissionRepository;
    private final UserRepository userRepository;
    private final ContestSubmissionConverter contestSubmissionConverter;

    @Override
    public PageResponse<?> getAllByUserId(Long userId, int pageNo, int pageSize) {
        if(userRepository.existsById(userId)) {
            int page = 0;
            if(pageNo > 0) {
                page = pageNo - 1;
            }
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<ContestSubmissionEntity> submissions = contestSubmissionRepository.findAllByContestUserExercise_ContestUserUserId(userId, pageable);

            return PageResponse.builder()
                    .page(pageNo)
                    .size(pageSize)
                    .totalPages(submissions.getTotalPages())
                    .totalElements(submissions.getTotalElements())
                    .items(submissions.stream().map(contestSubmissionConverter::toDto).toList())
                    .build();
        }
        throw new ResourceNotFoundException("Student not found");
    }

    @Override
    public PageResponse<?> getAllByUserIdAndContestId(Long id, int pageNo, int pageSize) {
        return null;
    }

}
