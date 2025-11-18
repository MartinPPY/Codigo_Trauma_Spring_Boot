package com.martin.codigo.trauma.app.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import com.martin.codigo.trauma.app.models.UserDto;

public interface UserService {

    ResponseEntity<Map<String,Object>> findEmail(Map<String,String> request);

    ResponseEntity<Map<String,Object>> save(UserDto userDto);

}
