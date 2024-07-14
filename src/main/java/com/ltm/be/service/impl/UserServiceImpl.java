package com.ltm.be.service.impl;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.converter.UserConverter;
import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.InvalidDataException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.UserRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Override
    public UserDto addUser(UserRequest request) {
        if(!userRepository.existsByStudentCode(request.getStudentCode())) {
            UserEntity userEntity = UserEntity.builder()
                    .studentCode(request.getStudentCode())
                    .ip(request.getIp())
                    .build();
            UserEntity userResponse = userRepository.save(userEntity);
            return userConverter.toDto(userResponse);
        }
        throw new InvalidDataException("User exists");
    }

    @Override
    public PageResponse<?> getAllUsers(int pageNo, int pageSize) {
        int page = 0;
        if(pageNo > 0) {
            page = pageNo - 1;
        }
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserEntity> users = userRepository.findAll(pageable);
        return PageResponse.builder()
                .page(pageNo)
                .size(pageSize)
                .totalPages(users.getTotalPages())
                .totalElements(users.getTotalElements())
                .items(users.stream().map(userConverter::toDto).toList())
                .build();
    }

    @Override
    public UserDto getUserByStudentCode(String studentCode) {
        if(userRepository.existsByStudentCode(studentCode)) {
            UserEntity userEntity = userRepository.findByStudentCode(studentCode);
            return userConverter.toDto(userEntity);
        }
        throw new ResourceNotFoundException("User not exists");
    }
    @Override
    public boolean existByStudentCode(String studentCode) {
        return userRepository.existsByStudentCode(studentCode);
    }
}
