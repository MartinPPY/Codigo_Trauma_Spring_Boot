package com.martin.codigo.trauma.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.martin.codigo.trauma.app.models.UserDto;

public interface UserService {

    ResponseEntity<Map<String, Object>> findEmail(Map<String, String> request);

    ResponseEntity<Map<String, Object>> registerUser(UserDto userDto);

    ResponseEntity<List<UserDto>> findAllMedics();

    ResponseEntity<List<UserDto>> findAllAvailabilityMedics(Boolean availability);

    ResponseEntity<?> findEmergency(String username);

}
