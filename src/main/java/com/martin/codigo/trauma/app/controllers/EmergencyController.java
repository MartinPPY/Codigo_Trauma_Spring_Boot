package com.martin.codigo.trauma.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.martin.codigo.trauma.app.models.EmergencyDto;
import com.martin.codigo.trauma.app.services.EmergencyService;
import com.martin.codigo.trauma.app.services.UserService;

@RestController
@RequestMapping("/emergencies")
public class EmergencyController {

    @Autowired
    private EmergencyService emergencyService;
    
    @Autowired
    private UserService userService;

    @PostMapping
    ResponseEntity<Map<String, Object>> createEmergency(@RequestBody EmergencyDto emergencyDto) {
        return emergencyService.registerEmergency(emergencyDto);
    }

    @GetMapping
    List<EmergencyDto> getEmergencies() {
        return emergencyService.findAllByOrderByCreation();
    }

    @GetMapping("/{username}")
    public ResponseEntity<?> getEmergency(@PathVariable String username) {
        System.out.println(username);
        return userService.findEmergency(username);
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Map<String, Object>> updateComments(@PathVariable Long id,
            @RequestBody Map<String, Object> request) {

        String comment = request.get("comment").toString();

        return emergencyService.updateComments(id, comment);
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Map<String, Object>> updateStatus(@PathVariable Long id,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");
        return emergencyService.updateStatus(id, status);
    }

}
