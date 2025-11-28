package com.martin.codigo.trauma.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martin.codigo.trauma.app.services.EmergencyService;
import com.martin.codigo.trauma.app.services.UserService;

@RestController
@RequestMapping("/medic")
public class MedicController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmergencyService emergencyService;

    @GetMapping("/emergency/{username}")
    public ResponseEntity<?> getEmergency(@PathVariable String username) {
        System.out.println(username);
        return userService.findEmergency(username);
    }

    @PutMapping("/emergency/{id}")
    public ResponseEntity<Map<String, Object>> updateComments(@PathVariable Long id,
            @RequestBody Map<String, Object> request) {
        
        String comment = request.get("comment").toString();

        return emergencyService.updateComments(id, comment);
    }

}
