package com.ltm.be.service;

import com.ltm.be.payload.request.LoginRequest;
import com.ltm.be.payload.request.RegistrationRequest;
import com.ltm.be.payload.response.LoginResponse;

public interface IAuthenticationService {
    LoginResponse register(RegistrationRequest request);
    LoginResponse login(LoginRequest request);
}
