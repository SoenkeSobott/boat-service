package com.example.boat;

import com.example.boat.entity.Boat;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class BoatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        // TODO: Consider setting up test data in the database before each test.
    }

    @AfterEach
    public void tearDown() {
        // TODO: Consider cleaning up test data in the database after each test.
    }

    @Test
    @WithMockUser
    public void whenPostBoatThenReturns201() throws Exception {
        Boat boat = new Boat();
        boat.setName("Boat1");
        boat.setDescription("Description1");

        mockMvc.perform(post("/api/boats")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(boat)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Boat1"))
                .andExpect(jsonPath("$.description").value("Description1"))
                .andDo(print());
    }

    @Test
    @WithMockUser
    public void whenGetBoatsThenReturns200() throws Exception {
        mockMvc.perform(get("/api/boats"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andDo(print());
    }

    // TODO: Test case where a specific boat is requested by ID. The response should be `200 OK` if the boat exists, and `404 Not Found` if it does not.
    // TODO: Test case where a boat is updated. The response should be `200 OK` if the boat exists, `400 Bad Request` if the data is invalid, and `404 Not Found` if the boat does not exist.
    // TODO: Test case where a boat is deleted. The response should be `204 No Content` if the deletion is successful, and `404 Not Found` if the boat does not exist.
    // TODO: Test case where a boat is created. The response should be `201 Created` if the creation is successful and `400 Bad Request` if the data is invalid.
    // TODO: Test case to validate the structure of the returned Boat object when a boat is created, updated, or fetched by ID.

}
