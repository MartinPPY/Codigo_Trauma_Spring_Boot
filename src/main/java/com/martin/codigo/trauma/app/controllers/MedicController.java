package com.martin.codigo.trauma.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martin.codigo.trauma.app.services.UserService;

@RestController
@RequestMapping("/medic")
public class MedicController {

    @Autowired
    private UserService userService;

    @GetMapping("/emergency/{username}")
    public ResponseEntity<?> getEmergency(@PathVariable String username) {
        System.out.println(username);
        return userService.findEmergency(username);
    }

}
