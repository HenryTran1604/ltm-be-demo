package com.ltm.be.service;

import com.ltm.be.dto.UserDto;
import com.ltm.be.payload.request.RegistrationRequest;

public interface IUserService {
    UserDto create(RegistrationRequest request);
    UserDto getByUsername(String userName);
    boolean existsByUsernameAndIp(String userName, String ip);
}
