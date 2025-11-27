package com.martin.codigo.trauma.app.services;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.martin.codigo.trauma.app.models.EmergencyDto;

public interface EmergencyService {

    ResponseEntity<Map<String, Object>> registerEmergency(EmergencyDto emergencyDto);

    List<EmergencyDto> findAllByOrderByCreation();

}
