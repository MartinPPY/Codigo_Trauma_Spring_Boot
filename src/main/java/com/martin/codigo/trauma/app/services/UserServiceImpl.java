package com.martin.codigo.trauma.app.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martin.codigo.trauma.app.entities.Emergency;
import com.martin.codigo.trauma.app.entities.User;
import com.martin.codigo.trauma.app.models.EmergencyDto;
import com.martin.codigo.trauma.app.models.UserDto;
import com.martin.codigo.trauma.app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<Map<String, Object>> findEmail(Map<String, String> request) {
        Map<String, Object> response = new HashMap<>();

        Optional<User> user = userRepository.findEmail(request.get("email"));

        if (!user.isPresent()) {
            response.put("message", "El usuario con el correo: " + request.get("email") + " no existe!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        response.put("verification", true);
        return ResponseEntity.ok().body(response);

    }

    @Transactional
    @Override
    public ResponseEntity<Map<String, Object>> registerUser(UserDto userDto) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> userDb = userRepository.findEmail(userDto.getEmail());

        if (!userDb.isPresent()) {
            response.put("message", "El usuario con el correo: " + userDto.getEmail() + " no existe!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        /* validar que el usuario no se haya registrado obteniendo el nombre */
        if (userDb.orElseThrow().getName() != "" && userDb.orElseThrow().getName() != null) {
            System.out.println(userDb.orElseThrow().getName());
            response.put("message", "no puedes registrarte nuevamente con este correo!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        /* validar que no exista el nombre de usuario ni el numero de telefono */
        if (userDb.get().getUsername() == userDto.getUsername()) {
            response.put("message", "El nombre de usuario ya existe!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        if (userDb.get().getPhone() == userDto.getPhone()) {
            response.put("message", "El numero de telefono ya existe!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        User user = userDb.orElseThrow();
        user.setUsername(userDto.getUsername());
        user.setName(userDto.getName());
        user.setLastname(userDto.getLastname());
        user.setPhone(userDto.getPhone());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);

        response.put("message", "Usuario actualizado correctamente!. Nomrbe de usuario: " + user.getUsername());

        return ResponseEntity.ok().body(response);

    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<UserDto>> findAllMedics() {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAllMedics());
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<List<UserDto>> findAllAvailabilityMedics(Boolean availability) {
        return ResponseEntity.status(HttpStatus.OK).body(userRepository.findAllAvailabilityMedics(availability));
    }

    @Transactional(readOnly = true)
    @Override
    public ResponseEntity<?> findEmergency(String username) {

        EmergencyDto emergencyDto = new EmergencyDto();
        Optional<User> userDb = userRepository.findByUsername(username);

        /* validar medico */
        if (!userDb.isPresent()) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario no existe!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        /* Encontrar la emergencia pendiente */
        for (Emergency emergency : userDb.orElseThrow().getEmergencies()) {
            System.out.println(emergency.getStatus());
            if (emergency.getStatus().equals("PENDING") || emergency.getStatus().equals("IN PROGRES") ){

                emergencyDto.setComments(emergency.getComments());
                emergencyDto.setCreation(emergency.getCreatedAt());
                emergencyDto.setDescription(emergency.getDescription());
                emergencyDto.setId(emergency.getId());
                emergencyDto.setUpdateAt(emergency.getUpdatedAt());
                emergencyDto.setSeverity(emergency.getSeverity());
                emergencyDto.setStatus(emergency.getStatus());
                emergencyDto.setVictims(emergency.getVictims());

                List<String> users = new ArrayList<>();
                for (User user : emergency.getUsers()) {
                    users.add(user.getName() + " " + user.getLastname());
                }
                emergencyDto.setMedicNames(users);
            }
        }

        return ResponseEntity.ok().body(emergencyDto);
    }

}
