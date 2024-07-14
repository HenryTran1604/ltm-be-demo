package com.ltm.be.service;

import com.ltm.be.dto.UserDto;
import com.ltm.be.payload.request.UserRequest;
import com.ltm.be.payload.response.PageResponse;

import java.util.List;

public interface IUserService {
    UserDto addUser(UserRequest user);
    PageResponse<?> getAllUsers(int pageNo, int pageSize);
    UserDto getUserByStudentCode(String studentCode);
    boolean existByStudentCode(String studentCode);
}
