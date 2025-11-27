package com.martin.codigo.trauma.app.e2e;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.martin.codigo.trauma.app.models.UserDto;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AdminTest {

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
        private ObjectMapper objectMapper;

        @Autowired
        private MockMvc mockMvc;

        @Test
        public void adminE2ETest() throws Exception {

                UserDto user = new UserDto("MartinTroolin", "Martin", "Romero", 964041720, "123",
                                "martinsantiago.se@gmail.com");

                Map<String, String> verificationRequest = new HashMap<>();
                verificationRequest.put("email", user.getEmail());

                Map<String, Object> request = new HashMap<>();
                request.put("username", "MartinTroolin");
                request.put("password", "123");

                /* verifica correo */
                ResultActions verificationResponse = mockMvc.perform(
                                post("/auth/verificate-email")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(verificationRequest)));

                verificationResponse.andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.verification").exists());

                /* registra usuario */
                ResultActions registerResponse = mockMvc.perform(
                                put("/auth/register-user")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(user)));

                registerResponse.andExpect(status().isOk());

                /* login */
                ResultActions response = mockMvc.perform(
                                post("/login").contentType(MediaType.APPLICATION_JSON)
                                                .content(objectMapper.writeValueAsString(request)));

                response.andExpect(status().isOk())
                                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                                .andExpect(jsonPath("$.token").exists());

                String responseBody = response.andReturn().getResponse().getContentAsString();
                JsonNode json = objectMapper.readTree(responseBody);

                String token = json.get("token").asText();

                /* lista medicos */
                mockMvc.perform(
                                get("/admin/medics")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .header("Authorization", "Bearer " + token))
                                .andExpect(status().isOk());

                Map<String, Object> emergencyDto = new HashMap<>();
                Integer[] medics = { 2, 3 };
                emergencyDto.put("description", "Paciente con hemorragia interna");
                emergencyDto.put("victims", 1);
                emergencyDto.put("severity", "HIGH");
                emergencyDto.put("medics", medics);

                /* crea emergencias */
                mockMvc.perform(
                                post("/admin/emergencies")
                                                .contentType(MediaType.APPLICATION_JSON)
                                                .header("Authorization", "Bearer " + token)
                                                .content(objectMapper.writeValueAsString(emergencyDto)))

                                .andExpect(status().isCreated());

        }

}