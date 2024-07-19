package com.ltm.be.service;

import com.ltm.be.exception.LoginFailException;
import com.ltm.be.payload.request.LoginRequest;
import com.ltm.be.payload.request.RegisterRequest;
import com.ltm.be.payload.response.LoginResponse;

public interface IAuthenticationService {
    LoginResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request) throws LoginFailException;
}
