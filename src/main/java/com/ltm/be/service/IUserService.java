package com.ltm.be.service;

import com.ltm.be.dto.UserDto;
import com.ltm.be.payload.request.RegistrationRequest;
import com.ltm.be.payload.response.PageResponse;

public interface IUserService {
    UserDto addUser(RegistrationRequest request);
    PageResponse<?> getAllUsers(int pageNo, int pageSize);
    UserDto getUserByUsername(String username);
    boolean existsByUsernameAndIp(String username, String ip);
}
