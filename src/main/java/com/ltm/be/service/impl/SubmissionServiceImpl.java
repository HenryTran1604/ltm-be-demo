package com.ltm.be.service.impl;

import com.ltm.be.converter.SubmissionConverter;
import com.ltm.be.entity.SubmissionEntity;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.repository.SubmissionRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.ISubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class SubmissionServiceImpl implements ISubmissionService {
    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SubmissionConverter submissionConverter;

    @Override
    public PageResponse<?> getAllByUserId(Long userId, int pageNo, int pageSize) {
        if(userRepository.existsById(userId)) {
            int page = 0;
            if(pageNo > 0) {
                page = pageNo - 1;
            }
            Pageable pageable = PageRequest.of(page, pageSize);
            Page<SubmissionEntity> submissions = submissionRepository.findAllByUserExercise_UserId(userId, pageable);

            return PageResponse.builder()
                    .page(pageNo)
                    .size(pageSize)
                    .totalPages(submissions.getTotalPages())
                    .totalElements(submissions.getTotalElements())
                    .items(submissions.stream().map(submissionConverter::toDto).toList())
                    .build();
        }
        throw new ResourceNotFoundException("Student not found");
    }

}
