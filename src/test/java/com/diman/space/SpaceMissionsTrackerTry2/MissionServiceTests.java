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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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

    @Test
    public void testGetMissionById() {
        Mission mission = new Mission(1L, "James Webb Space Telescope", LocalDate.of(2021, 12, 15), "Ongoing", "Large space-based observatory.");

        when(repository.findById(1L))
                .thenReturn(Optional.of(mission));

        Mission result = service.getMissionById(1L);

        assertNotNull(result);
        assertEquals(mission.getName(), result.getName());
    }

    @Test
    public void testSaveMission() {
        Mission mission = new Mission(1L, "BepiColombo - Mercury", LocalDate.of(2018, 10, 20), "Ongoing", "Mercury exploration mission.");

        when(repository.save(any(Mission.class)))
                .thenReturn(mission);

        Mission result = service.saveMission(mission);

        assertNotNull(result);
        assertEquals(mission.getName(), result.getName());
    }

    @Test
    public void testDeleteMission() {
        doNothing()
                .when(repository).deleteById(1L);

        service.deleteMission(1L);

        verify(repository, times(1)).deleteById(1L);
    }

}
