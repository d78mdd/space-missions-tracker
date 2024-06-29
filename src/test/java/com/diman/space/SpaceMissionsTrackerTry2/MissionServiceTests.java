package com.diman.space.SpaceMissionsTrackerTry2;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import com.diman.space.SpaceMissionsTrackerTry2.dao.MissionDao;
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
    private MissionDao dao;

    @Autowired
    private MissionService service;


    @Test
    public void testGetAllMissions() {
        Mission mission1 = new Mission(1L, "Sputnik 1", LocalDate.of(1957, 10, 4), "Completed", "First artificial Earth satellite.");
        Mission mission2 = new Mission(2L, "Artemis I", LocalDate.of(2024, 12, 15), "Planned", "First mission of NASA's Artemis program.");
        List<Mission> missions = Arrays.asList(mission1, mission2);

        when(dao.findAll())
                .thenReturn(missions);

        List<Mission> result = service.getAllMissions();

        assertEquals(2, result.size());
        assertEquals(mission1.getName(), result.get(0).getName());
        assertEquals(mission2.getName(), result.get(1).getName());
    }

    @Test
    public void testGetMissionById() {
        Mission mission = new Mission(1L, "James Webb Space Telescope", LocalDate.of(2021, 12, 15), "Ongoing", "Large space-based observatory.");

        when(dao.findById(1L))
                .thenReturn(Optional.of(mission));

        Mission result = service.getMissionById(1L);

        assertNotNull(result);
        assertEquals(mission.getName(), result.getName());
    }

    @Test
    public void testSaveMission() {
        Mission mission = new Mission(1L, "BepiColombo - Mercury", LocalDate.of(2018, 10, 20), "Ongoing", "Mercury exploration mission.");

        doNothing()
                .when(dao).insert(any(Mission.class));

        service.saveMission(mission);

        verify(dao, times(1)).insert(any(Mission.class));
    }

    @Test
    public void testDeleteMission() {
        doNothing()
                .when(dao).deleteById(1L);

        service.deleteMission(1L);

        verify(dao, times(1)).deleteById(1L);
    }


    @Test
    public void testSearchMissionsByName() {
        Mission mission1 = new Mission(1L, "Sputnik 1", LocalDate.of(1957, 10, 4), "Completed", "First artificial Earth satellite.");
        List<Mission> missions = List.of(mission1);

        when(dao.findByNameContainingIgnoreCase("Sputnik 1"))
                .thenReturn(missions);

        List<Mission> result = service.searchMissionsByName("Sputnik 1");

        assertEquals(1, result.size());
        assertEquals(mission1.getName(), result.get(0).getName());

        verify(dao, times(0)).findByLaunchDate(any(LocalDate.class));
        verify(dao, times(0)).findAll();
    }

    @Test
    public void testSearchMissionsByDate() {
        Mission mission1 = new Mission(1L, "Sputnik 1", LocalDate.of(1957, 10, 4), "Completed", "First artificial Earth satellite.");
        List<Mission> missions = List.of(mission1);

        when(dao.findByLaunchDate(LocalDate.of(1957,10,4)))
                .thenReturn(missions);

        List<Mission> result = service.searchMissionsByDate(LocalDate.of(1957,10,4));

        assertEquals(1, result.size());
        assertEquals(mission1.getName(), result.get(0).getName());

        verify(dao, times(0)).findByNameContainingIgnoreCase(any(String.class));
        verify(dao, times(0)).findAll();
    }

    // TODO add more service tests?

}
