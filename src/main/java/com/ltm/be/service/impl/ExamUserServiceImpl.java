package com.ltm.be.service.impl;

import com.ltm.be.converter.ExamConverter;
import com.ltm.be.converter.ExamUserConverter;
import com.ltm.be.converter.UserConverter;
import com.ltm.be.dto.ExamUserDto;
import com.ltm.be.entity.ExamEntity;
import com.ltm.be.entity.ExamUserEntity;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.ExamRegistrationRequest;
import com.ltm.be.payload.request.ExamUserRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.ExamRepository;
import com.ltm.be.repository.ExamUserRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IExamUserService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamUserServiceImpl extends BaseServiceImpl<ExamUserDto, ExamUserEntity> implements IExamUserService {
    private final ExamUserRepository examUserRepository;
    private final ExamRepository examRepository;
    private final ExamConverter examConverter;
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public ExamUserServiceImpl(ExamConverter examConverter,
                               ExamUserConverter examUserConverter,
                               ExamRepository examRepository,
                               UserRepository userRepository,
                               UserConverter userConverter,
                               ExamUserRepository examUserRepository) {
        super(examUserRepository, examUserConverter);
        this.examRepository = examRepository;
        this.examConverter = examConverter;
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.examUserRepository = examUserRepository;
    }

    @Override
    public void create(ExamUserRequest request) {
        ExamEntity exam = examRepository.findById(request.getExamId()).orElseThrow(() -> new ResourceNotFoundException("exam not exist!"));
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
        List<ExamUserEntity> existingExamUsers = examUserRepository.findAllByExamIdAndUserIdIn(request.getExamId(), request.getUserIds());

        if (!existingExamUsers.isEmpty()) {
            Set<Long> existingUserIds = existingExamUsers.stream()
                    .map(examUser -> examUser.getUser().getId())
                    .collect(Collectors.toSet());
            throw new DataConflictException("Users with IDs " + existingUserIds + " have already joined this exam!");
        }
        List<ExamUserEntity> ExamUsers = users.stream().map(
                user -> ExamUserEntity.builder()
                        .exam(exam)
                        .user(user)
                        .build()
        ).toList();
        examUserRepository.saveAll(ExamUsers);
    }

    @Override
    public void register(ExamRegistrationRequest request) {
        if (examUserRepository.existsByExamIdAndUserId(request.getExamId(), request.getUserId())) {
            throw new DataConflictException("User registered already!");
        }
        ExamUserEntity userExam = ExamUserEntity.builder()
                .user(userRepository.getReferenceById(request.getUserId()))
                .exam(examRepository.getReferenceById(request.getExamId()))
                .build();
        examUserRepository.save(userExam);
    }

    @Override
    public PageResponse<?> getAllExamWithRegistration(Long userId, int pageNo, int pageSize) {
        UserEntity user = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exists"));
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamEntity> exams = examRepository.findAll(pageable);
        List<ExamUserDto> examUsers = new ArrayList<>();
        for (ExamEntity exam : exams) {
            ExamUserDto userExam = ExamUserDto.builder()
                    .exam(examConverter.toDto(exam))
                    .user(userConverter.toDto(user))
                    .registered(examUserRepository.existsByExamIdAndUserId(exam.getId(), userId))
                    .build();
            examUsers.add(userExam);
        }
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(exams.getTotalPages())
                .totalElements(exams.getTotalElements())
                .items(examUsers)
                .build();
    }

    @Override
    public PageResponse<?> getAllRegisteredUserByExamId(Long examId, int pageNo, int pageSize) {
        int page = pageNo > 0 ? pageNo - 1 : pageNo;
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<ExamUserEntity> usersInExam = examUserRepository.findAllByExamId(examId, pageable);
        return getPageByList(usersInExam, pageNo, pageSize);
    }
}
