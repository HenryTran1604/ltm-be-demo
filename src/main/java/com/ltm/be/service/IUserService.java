package com.ltm.be.service;

import com.ltm.be.dto.UserDto;

import java.util.List;

public interface IUserService {
    UserDto addUser(UserDto user);
    List<UserDto> getAllUsers();
    UserDto getUserByStudentCode(String studentCode);
    boolean existByStudentCode(String studentCode);
}
