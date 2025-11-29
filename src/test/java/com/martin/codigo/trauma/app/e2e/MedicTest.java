package com.martin.codigo.trauma.app.e2e;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.martin.codigo.trauma.app.models.UserDto;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class MedicTest {

        @Container
        static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16")
                        .withDatabaseName("test_db")
                        .withUsername("test")
                        .withPassword("test")
                        .withInitScript("db/init.sql");

        @DynamicPropertySource
        static void registryPgProperties(DynamicPropertyRegistry registry) {
                registry.add("spring.datasource.url", postgres::getJdbcUrl);
                registry.add("spring.datasource.username", postgres::getUsername);
                registry.add("spring.datasource.password", postgres::getPassword);

        }

        @Autowired
        private MockMvc mockMvc;

        @Autowired
        private ObjectMapper objectMapper;

        @Test
        public void MedicE2ETest() throws Exception {

                UserDto user = new UserDto("carla.torres", "Carla", "Torres", 111111111, "123",
                                "martinsantiago.se@gmail.com");


                Map<String, String> verificationRequest = new HashMap<>();
                verificationRequest.put("email", user.getEmail());

                Map<String, Object> request = new HashMap<>();
                request.put("username", user.getUsername());
                request.put("password", user.getPassword());

                /* VERIFICAR USER */

                ResultActions verificationResponse = mockMvc.perform(
                                post("/auth/verificate-email")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(verificationRequest)));

                verificationResponse.andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.verification").exists());

                /* REGISTRARSE */
                ResultActions registerResponse = mockMvc.perform(
                                put("/auth/register-user")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(user)));

                registerResponse.andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.message").exists());

                /* LOGIN DEL MEDICO */
                ResultActions response = mockMvc.perform(
                                post("/login").contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(request)));

                response.andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").exists());
                
                /* VER LA EMERGENCIA */        
                
        }

}
