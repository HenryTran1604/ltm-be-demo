package com.ltm.be.controller;

import com.ltm.be.dto.UserDto;
import com.ltm.be.exception.InvalidDataException;
import com.ltm.be.exception.ResourceNotFoundException;
import com.ltm.be.payload.request.UserRequest;
import com.ltm.be.payload.response.PageResponse;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.payload.response.ResponseError;
import com.ltm.be.service.IUserService;
import com.ltm.be.util.StudentCode;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Validated
@RequiredArgsConstructor
@Tag(name = "User Controller")
public class UserController {
    private final IUserService userService;
    @Operation(
            summary = "Get all users"
    )
    @GetMapping("/users")
    public ResponseData<?> getAllUsers(@RequestParam(defaultValue = "0", required = false) int pageNo,
                                       @RequestParam(defaultValue = "50", required = false) int pageSize) {
        return new ResponseData<>(HttpStatus.OK.value(), "users", userService.getAllUsers(pageNo, pageSize));
    }
    @Operation(
            summary = "Register new user"
    )
    @PostMapping("/register")
    public ResponseData<?> addUser(@Valid @RequestBody UserRequest user) {
        try {
            UserDto userDto = userService.addUser(user);
            return new ResponseData<>(HttpStatus.CREATED.value(), "Add user successfully!", userDto);
        } catch (InvalidDataException exception) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Student has already registerd");
        }
    }

    @GetMapping("/test")
    public ResponseData<?> test(@StudentCode @RequestParam String studentCode) {
        System.out.println(studentCode);
        try {
            UserDto userDto = userService.getUserByStudentCode(studentCode);
            return new ResponseData<>(HttpStatus.OK.value(), "register successfully!", userDto);
        } catch (Exception exception) {
            return new ResponseError<>(HttpStatus.BAD_REQUEST.value(), "Student not exists");
        }
    }
}
