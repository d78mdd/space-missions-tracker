package com.diman.space.SpaceMissionsTrackerTry2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class MissionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetAllMissions() throws Exception {
        mockMvc.perform(post("/api/missions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Rosetta\", \"launchDate\": \"2004-03-02\", \"status\":\"Completed\", \"description\":\"ESA mission to study commet 67P/Churyumov-Gerasimenko, including the Philae lander.\"}"));
        mockMvc.perform(post("/api/missions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Luna 2\", \"launchDate\": \"1952-09-12\", \"status\":\"Completed\", \"description\":\"First spacecraft to reach the Moon.\"}"));

        mockMvc.perform(get("/api/missions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", is("Rosetta")))
                .andExpect(jsonPath("$[1].name", is("Luna 2")));
    }

    @Test
    public void testGetMissionsByDate() throws Exception {
        mockMvc.perform(post("/api/missions")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\": \"Vostok 1\", \"launchDate\": \"1961-04-12\", \"status\":\"Completed\", \"description\":\"First human spaceflight, carried Yuri Gagarin into orbit.\"}"));

        mockMvc.perform(get("/api/missions/search?date=1961-04-12")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is("Vostok 1")));
    }

    @Test
    public void testGetMissionByIdNotFound() throws Exception {

        mockMvc.perform(get("/api/missions/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testCreateMission() throws Exception {

        mockMvc.perform(post("/api/missions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"Skylab\", \"launchDate\": \"1973-05-14\", \"status\":\"Completed\", \"description\":\"First United States space station.\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/missions/1"))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("Skylab")))
                .andExpect(jsonPath("$.launchDate", is("1973-05-14")))
                .andExpect(jsonPath("$.status", is("Completed")))
                .andExpect(jsonPath("$.description", is("First United States space station.")));
    }

}
