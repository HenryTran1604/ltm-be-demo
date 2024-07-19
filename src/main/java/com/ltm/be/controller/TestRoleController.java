package com.ltm.be.controller;

import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestRoleController {
    @GetMapping("/roletest")
    public void welcome(SecurityContextHolderAwareRequestWrapper request) {
        boolean b = request.isUserInRole("ROLE_ADMIN");
        System.out.println("ROLE_ADMIN=" + b);

        boolean c = request.isUserInRole("ROLE_USER");
        System.out.println("ROLE_USER=" + c);
    }
}
