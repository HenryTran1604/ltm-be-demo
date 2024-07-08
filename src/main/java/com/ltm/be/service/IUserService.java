package com.ltm.be.service;

import com.ltm.be.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto addUser(UserDto userDto);
    boolean isUserExistedWithId(UserDto userDto);
    boolean isUserExistedWithUidAndIP(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(String id);
    UserDto updateUser(UserDto userDto);
    void deleteUser(UserDto userDto);
}
