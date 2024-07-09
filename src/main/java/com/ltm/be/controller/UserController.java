package com.ltm.be.controller;

import com.ltm.be.dto.UserDto;
import com.ltm.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
        if(userService.existByStudentCode(user.getStudentCode())) {

            return ResponseEntity.badRequest().body("Mã sinh viên đã được đăng kí!");
        }
        UserDto register = userService.addUser(user);
        return new ResponseEntity<>(register, HttpStatus.OK);
    }

    @GetMapping("/test")
    public ResponseEntity<?> test(@RequestParam String studentCode) {
        return new ResponseEntity<>(userService.existByStudentCode(studentCode), HttpStatus.OK);
    }
}
