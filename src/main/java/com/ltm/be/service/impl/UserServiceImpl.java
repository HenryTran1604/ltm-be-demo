package com.ltm.be.service.impl;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.exception.DataConflictException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.exception.UsernameAndIpAlreadyExistException;
import com.ltm.be.payload.request.RegistrationRequest;
import com.ltm.be.repository.RoleRepository;
import com.ltm.be.repository.UserRepository;
import com.ltm.be.service.IUserService;
import com.ltm.be.service.base.BaseServiceImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDto, UserEntity> implements IUserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter,
                           PasswordEncoder passwordEncoder,
                           RoleRepository roleRepository) {
        super(userRepository, userConverter);
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDto create(RegistrationRequest request) {
        checkExistedUser(request.getUsername(), request.getIp());
        UserEntity userEntity = UserEntity.builder()
                .userName(request.getUsername().toLowerCase())
                .ipAddress(request.getIp())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(roleRepository.findByName("ROLE_USER").orElseThrow(() -> new ResourceNotFoundException("Role user not exist")))
                .build();
        UserEntity userResponse = userRepository.save(userEntity);
        return create(userResponse);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserEntity userEntity = userRepository.findByUserName(username).orElseThrow(() -> new ResourceNotFoundException("User not exists"));
        return userConverter.toDto(userEntity);
    }

    @Override
    public boolean existsByUsernameAndIp(String username, String ip) {
        return userRepository.existsByUserNameAndIpAddress(username, ip);
    }

    private void checkExistedUser(String username, String ip) {
        checkExistedUsername(username);
//        checkExistedUsernameAndIp(username, ip);
    }

    private void checkExistedUsername(String username) {
        if (userRepository.existsByUserName(username)) {
            throw new DataConflictException("Username already existed");
        }
    }

    private void checkExistedUsernameAndIp(String username, String ip) {
        if (userRepository.existsByUserNameAndIpAddress(username, ip)) {
            throw new UsernameAndIpAlreadyExistException("Username already registered with another ip!");
        }
    }

}
