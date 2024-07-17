package com.ltm.be.service.impl;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.InvalidDataException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.RegisterRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.repository.RoleRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Override
    public UserDto addUser(RegisterRequest request) {
        checkExistedUser(request.getStudentCode(), request.getIp());
        UserEntity userEntity = UserEntity.builder()
                .studentCode(request.getStudentCode())
                .ip(request.getIp())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findByName("USER").orElseThrow(() -> new ResourceNotFoundException("Role user not exist")))
                .build();
        UserEntity userResponse = userRepository.save(userEntity);
        return userConverter.toDto(userResponse);
    }
    @Override
    public PageResponse<?> getAllUsers(int pageNo, int pageSize) {
        int page = 0;
        if (pageNo > 0) {
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
        UserEntity userEntity = userRepository.findByStudentCode(studentCode).orElseThrow(() -> new ResourceNotFoundException("User not exists"));
        return userConverter.toDto(userEntity);
    }

    @Override
    public boolean existByStudentCode(String studentCode) {
        return userRepository.existsByStudentCode(studentCode);
    }

    private void checkExistedUser(String username, String ip) {
        checkExistedUsername(username);
//        checkExistedUsernameAndIp(username, ip);
    }

    private void checkExistedUsername(String username) {
        if (userRepository.existsByStudentCode(username)) {
            throw new InvalidDataException("Username already existed");
        }
    }

    private void checkExistedUsernameAndIp(String username, String ip) {
        if (userRepository.existsByStudentCodeAndIp(username, ip)) {
            throw new InvalidDataException("Username already registered with specific ip!");
        }
    }

}
