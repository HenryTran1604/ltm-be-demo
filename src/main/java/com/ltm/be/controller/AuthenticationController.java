package com.ltm.be.controller;

import com.ltm.be.exception.LoginFailException;
import com.ltm.be.payload.request.LoginRequest;
import com.ltm.be.payload.request.RegisterRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Validated
@Tag(name = "Authentication Controller")

public class AuthenticationController {
    private final IAuthenticationService authenticationService;
    @PostMapping("/login")
    public ResponseData<?> login(@Valid @RequestBody LoginRequest request) {
            return new ResponseData<>(HttpStatus.OK.value(),
                    "login successfully!",
                    authenticationService.login(request));
    }

    @PostMapping("/register")
    @Operation(
            summary = "Register new user"
    )
    public ResponseData<?> register(@Valid @RequestBody RegisterRequest request) {
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "login successfully!",
                authenticationService.register(request));
    }
    @GetMapping("/test")
//    @CrossOrigin("*")
    public String test() {
        return "dsa;jfdsa";
    }

}
