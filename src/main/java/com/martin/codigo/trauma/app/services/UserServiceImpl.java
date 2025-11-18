package com.martin.codigo.trauma.app.services;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.martin.codigo.trauma.app.entities.User;
import com.martin.codigo.trauma.app.models.UserDto;
import com.martin.codigo.trauma.app.repositories.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
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
    public ResponseEntity<Map<String, Object>> save(UserDto userDto) {
        Map<String, Object> response = new HashMap<>();
        Optional<User> userDb = userRepository.findEmail(userDto.getEmail());

        if (!userDb.isPresent()) {
            response.put("message", "El usuario con el correo: " + userDto.getEmail() + " no existe!");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
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

}
