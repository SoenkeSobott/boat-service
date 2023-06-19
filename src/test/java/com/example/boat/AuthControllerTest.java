package com.example.boat;

import com.example.boat.entity.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void whenValidInputThenReturns200() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("Anna");
        loginRequest.setPassword("save-password");

        mockMvc.perform(post("/api/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(jsonPath("$.token").exists())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenInvalidInputThenReturns404() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername("InvalidUsername");
        loginRequest.setPassword("InvalidPassword");

        mockMvc.perform(post("/api/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isNotFound())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void whenNoUsernameThenReturns400() throws Exception {
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setPassword("save-password");

        mockMvc.perform(post("/api/login")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(loginRequest)))
                .andExpect(status().isBadRequest())
                .andDo(MockMvcResultHandlers.print());
    }

    // TODO: Test case where the password is not provided. The response should be `400 Bad Request`.
    // TODO: Test case where both username and password are not provided. The response should be `400 Bad Request`.
    // TODO: Test case where a valid username is provided but with an invalid password. The response should be `404 Not Found`.
    // TODO: Test case where a valid password is provided but with an invalid username. The response should be `404 Not Found`.
    // TODO: Test case to validate the JWT token structure in response when a valid username and password are provided.

}
