package com.example.fellon_crm.controller;

import com.example.fellon_crm.entity.User;
import com.example.fellon_crm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestParam String username,
                           @RequestParam String email,
                           @RequestParam String password) {
        return userService.createUser(username, email, password);

    }
}