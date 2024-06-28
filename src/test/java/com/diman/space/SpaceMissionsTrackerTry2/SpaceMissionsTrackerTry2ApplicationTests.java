package com.diman.space.SpaceMissionsTrackerTry2;

import com.diman.space.SpaceMissionsTrackerTry2.controller.MissionController;
import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import com.diman.space.SpaceMissionsTrackerTry2.service.MissionService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MissionController.class)
class SpaceMissionsTrackerTry2ApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private MissionService missionService;

	@Test
	public void getAllMissions() throws Exception {
		Mission mission1 = new Mission(1L, "Apollo 11", LocalDate.of(1969, 7, 16), "Completed", "First manned Moon landing.");
		Mission mission2 = new Mission(2L, "Mars Rover", LocalDate.of(2020, 7, 30), "Ongoing", "Mars exploration rover.");

		List<Mission> missions = Arrays.asList(mission1, mission2);

		Mockito.when(missionService.getAllMissions())
				.thenReturn(missions);

		mockMvc.perform(MockMvcRequestBuilders.get("/api/missions")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].name", is(mission1.getName())))
				.andExpect(jsonPath("$[1].name", is(mission2.getName())));
	}





}
