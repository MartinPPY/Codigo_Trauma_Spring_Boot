package com.martin.codigo.trauma.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martin.codigo.trauma.app.entities.Emergency;
import com.martin.codigo.trauma.app.entities.User;
import com.martin.codigo.trauma.app.models.EmergencyDto;
import com.martin.codigo.trauma.app.repositories.EmergencyRepository;
import com.martin.codigo.trauma.app.repositories.UserRepository;

@Service
public class EmergencyServiceImpl implements EmergencyService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmergencyRepository emergencyRepository;

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> registerEmergency(EmergencyDto emergencyDto) {

        Map<String, Object> response = new HashMap<>();
        List<User> medics = new ArrayList<>();

        /* Validar que existen los medicos */
        for (Integer id : emergencyDto.getMedics()) {
            Optional<User> userDb = userRepository.findById(id.longValue());
            if (!userDb.isPresent()) {
                response.put("message", "El medico no existe en la base de datos!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            /* validar que esten disponibles */
            if (userDb.orElseThrow().getAvailability() == false) {
                response.put("message", "Medico no disponible. Escoja otro!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            /* validar que los usuarios no sean administradores */
            if (userDb.orElseThrow().getRole().getId() == 1) {
                response.put("message", "Los administradores no pueden atender emergencias!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            /* agregar a los medicos a la lista de usuarios */
            medics.add(userDb.orElseThrow());

        }

        Emergency emergency = new Emergency(emergencyDto.getDescription(), emergencyDto.getVictims(),
                emergencyDto.getSeverity(), medics);

        emergencyRepository.save(emergency);

        response.put("message", "emergencia creada!, se les informara a los medicos!");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public List<EmergencyDto> findAllByOrderByCreation() {
        List<EmergencyDto> emergencies = new ArrayList<>();
        List<String> medicsNames = new ArrayList<>();

        List<Emergency> emergenciesDb = emergencyRepository.findAllByOrderByCreation();

        for (Emergency emergency : emergenciesDb) {

            for (User user : emergency.getUsers()) {
                medicsNames.add(user.getName() + " " + user.getLastname());
            }

            EmergencyDto emergencyDto = new EmergencyDto(emergency.getId(), emergency.getDescription(),
                    emergency.getVictims(),
                    emergency.getSeverity(), emergency.getStatus(), emergency.getComments(), emergency.getCreatedAt(),
                    emergency.getUpdatedAt(), emergency.getFinishedAt(), medicsNames);
            emergencies.add(emergencyDto);
        }

        return emergencies;
    }

}
