package com.ltm.be.service.impl;

import com.ltm.be.converter.UserConverter;
import com.ltm.be.dao.UserDAO;
import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.util.List;
@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserConverter userConverter;

    @Override
    public UserDto addUser(UserDto userDto) {
        UserEntity entity = userConverter.toEntity(userDto);
        entity.setCreatedAt(LocalDateTime.now());
        userDAO.saveUser(entity);
        return userConverter.toDto(entity);
    }

    @Override
    public boolean isUserExistedWithId(UserDto userDto) {
        return userDAO.getUserById(userDto.getId()) != null;
    }

    @Override
    public boolean isUserExistedWithUidAndIP(UserDto userDto) {
        return false;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userDAO.getAllUsers().stream().map(userConverter::toDto).toList();
    }

    @Override
    public UserDto getUserById(String id) {
        UserEntity entity = userDAO.getUserById(id);
        return userConverter.toDto(entity);
    }

    @Override
    public UserDto updateUser(UserDto userDto) {
        UserEntity entity = userConverter.toEntity(userDto);
        userDAO.updateUser(entity);
        return userConverter.toDto(entity);
    }

    @Override
    public void deleteUser(UserDto userDto) {

    }
}
