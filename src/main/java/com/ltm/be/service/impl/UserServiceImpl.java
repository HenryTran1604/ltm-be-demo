package com.ltm.be.service.impl;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.converter.UserConverter;
import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto addUser(UserDto user) {
        user.setCreatedAt(LocalDateTime.now());
        UserEntity userEntity = userRepository.save(userConverter.toEntity(user));
        return userConverter.toDto(userEntity);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(userConverter::toDto).toList(); // immutable
    }

    @Override
    public UserDto getUserByStudentCode(String studentCode) {
        UserEntity user = userRepository.findByStudentCode(studentCode);
        return userConverter.toDto(user);
    }

    @Override
    public boolean existByStudentCode(String studentCode) {
        return userRepository.existsByStudentCode(studentCode);
    }
}
