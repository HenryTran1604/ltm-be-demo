package com.ltm.be.service;

import com.ltm.be.dto.UserDto;
import com.ltm.be.entity.UserEntity;
import com.ltm.be.payload.request.RegistrationRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.service.base.IBaseService;

public interface IUserService extends IBaseService<UserDto, UserEntity, Long> {
    UserDto create(RegistrationRequest request);
    UserDto getUserByUsername(String username);
    boolean existsByUsernameAndIp(String username, String ip);
}
