package com.martin.codigo.trauma.app.e2e;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.martin.codigo.trauma.app.models.UserDto;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void RegisterAndLoginSuccess() throws Exception {

        UserDto user = new UserDto("MartinTroolin", "Martin", "Romero", 964041720, "123",
                "martinsantiago.se@gmail.com");

        Map<String, String> verificationRequest = new HashMap<>();
        verificationRequest.put("email", user.getEmail());

        Map<String, Object> request = new HashMap<>();
        request.put("username", "MartinTroolin");
        request.put("password", "123");

        ResultActions verificationResponse = mockMvc.perform(
                post("/auth/verificate-email")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(verificationRequest)));

        verificationResponse.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.verification").exists());

        ResultActions registerResponse = mockMvc.perform(
                put("/auth/register-user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)));

        registerResponse.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").exists());

        ResultActions response = mockMvc.perform(
                post("/login").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)));

        response.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.token").exists());

    }

}
