package com.ltm.be.controller;

import com.ltm.be.payload.request.RoleRequest;
import com.ltm.be.payload.response.ResponseData;
import com.ltm.be.service.IRoleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/roles")
@Tag(name = "Role Controller")
public class RoleController {
    private IRoleService roleService;
    @PostMapping("/add")
    public ResponseData<?> create(@RequestBody RoleRequest request) {
        return new ResponseData<>(HttpStatus.CREATED.value(),
                "Add role successfully!",
                roleService.create(request));
    }
}
