package com.martin.codigo.trauma.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martin.codigo.trauma.app.models.UserDto;
import com.martin.codigo.trauma.app.services.UserService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/verificate-email")
    ResponseEntity<Map<String, Object>> verificateEmail(@RequestBody Map<String,String> request) {
        return userService.findEmail(request);
    }

    @PutMapping("/register-user")
    ResponseEntity<Map<String, Object>> updateUser(@RequestBody UserDto userDto) {
        return userService.registerUser(userDto);
    }




}
