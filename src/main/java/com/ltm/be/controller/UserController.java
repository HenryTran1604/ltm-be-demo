package com.ltm.be.controller;

import com.ltm.be.dto.UserDto;
import com.ltm.be.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<?> addUser(@RequestBody UserDto user) {
        if(userService.isUserExistedWithId(user)) {
            return ResponseEntity.badRequest().body("Mã sinh viên đã được đăng kí!");
        }
        UserDto dto = userService.addUser(user);
        return ResponseEntity.ok(dto);
    }
//    @PostMapping("/users/update")
//    public ResponseEntity<?> updateUser(@RequestBody UserDto user) {
//
//    }
}
