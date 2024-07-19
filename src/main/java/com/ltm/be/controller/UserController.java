package com.ltm.be.controller;

import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Validated
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {
    private final IUserService userService;
    @Operation(
            summary = "Get all users"
    )
    @GetMapping("/all")
    public ResponseData<?> getAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                       @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(), "users", userService.getAllUsers(pageNo, pageSize));
    }
}
