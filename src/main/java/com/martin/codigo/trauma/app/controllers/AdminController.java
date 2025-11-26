package com.martin.codigo.trauma.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.martin.codigo.trauma.app.models.UserDto;
import com.martin.codigo.trauma.app.services.UserService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @GetMapping("/medics")
    ResponseEntity<List<UserDto>> getMedics(@RequestParam(required = false) Boolean availability) {
        if (availability == null) {
            return userService.findAllMedics();
        }
        return userService.findAllAvailabilityMedics(availability);
    }

    @PostMapping("/emergencies")
    ResponseEntity<Map<String, Object>> createEmergency() {

        return null;
    }

    @GetMapping("/emergencies")
    ResponseEntity<Map<String, Object>> getEmergencies() {

        return null;
    }

}
