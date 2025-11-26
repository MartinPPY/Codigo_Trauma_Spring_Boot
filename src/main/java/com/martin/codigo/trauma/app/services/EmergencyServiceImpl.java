package com.martin.codigo.trauma.app.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martin.codigo.trauma.app.entities.Emergency;
import com.martin.codigo.trauma.app.entities.User;
import com.martin.codigo.trauma.app.entities.UserEmergency;
import com.martin.codigo.trauma.app.models.EmergencyDto;
import com.martin.codigo.trauma.app.repositories.EmergencyRepository;
import com.martin.codigo.trauma.app.repositories.UserEmergencyRepository;
import com.martin.codigo.trauma.app.repositories.UserRepository;

@Service
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmergencyRepository emergencyRepository;

    @Autowired
    private UserEmergencyRepository userEmergencyRepository;

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> registerEmergency(EmergencyDto emergencyDto) {

        Map<String, Object> response = new HashMap<>();

        /* Validar que existen los medicos */
        for (Integer id : emergencyDto.getMedics()) {
            Optional<User> userDb = userRepository.findById(id.longValue());
            if (!userDb.isPresent()) {
                response.put("message", "El medico no existe en la base de datos!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        }

        Emergency emergency = new Emergency(emergencyDto.getDescription(), emergencyDto.getVictims());
        emergencyRepository.save(emergency);

        /* inserta en la tabla users_emergencies */
        for (Integer id : emergencyDto.getMedics()) {
            Optional<User> userDb = userRepository.findById(id.longValue());
            User user = userDb.orElseThrow();
            UserEmergency userEmergency = new UserEmergency(user, emergency, emergencyDto.getSeverity());
            userEmergencyRepository.save(userEmergency);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
