package com.diman.space.SpaceMissionsTrackerTry2;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import com.diman.space.SpaceMissionsTrackerTry2.repository.MissionRepository;
import com.diman.space.SpaceMissionsTrackerTry2.service.MissionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
public class MissionServiceTests {

    @MockBean
    private MissionRepository repository;

    @Autowired
    private MissionService service;


    @Test
    public void testGetAllMissions() {
        Mission mission1 = new Mission(1L, "Sputnik 1", LocalDate.of(1957, 10, 4), "Completed", "First artificial Earth satellite.");
        Mission mission2 = new Mission(2L, "Artemis I", LocalDate.of(2024, 12, 15), "Planned", "First mission of NASA's Artemis program.");
        List<Mission> missions = Arrays.asList(mission1, mission2);

        when(repository.findAll())
                .thenReturn(missions);

        List<Mission> result = service.getAllMissions();

        assertEquals(2, result.size());
        assertEquals(mission1.getName(), result.get(0).getName());
        assertEquals(mission2.getName(), result.get(1).getName());
    }






















}
