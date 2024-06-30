package com.diman.space.SpaceMissionsTrackerTry2.dao;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class MissionDaoTests {

    @Autowired
    private MissionDao dao;


    // TODO shorten lines / extract test data for missions as beforeTest


    @Test
    void findByNameContainingIgnoreCase() {
    }

    @Test
    void findByExactName() {
    }

    @Test
    void findByLaunchDate() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findById() {
        Mission mission = new Mission("Curiosity Rover", LocalDate.of(2011, 11, 26), "Ongoing", "Mars rover exploring the surface of Mars");
        dao.insert(mission);

        Mission result = dao.findById(1L).get();

        assertNotNull(result);
        assertEquals(mission.getName(), result.getName());
    }

    @Test
    void insert() {
        Mission mission = new Mission("Hubble Space Telescope", LocalDate.of(1990, 4, 24), "Ongoing", "Orbiting telescope for astronomical observations");

        dao.insert(mission);

        Mission insertedMission = dao.findById(1L).get();
        assertNotNull(insertedMission);
        assertEquals(mission.getName(), insertedMission.getName());
    }

    @Test
    void deleteById() {
        Mission mission = new Mission("ISS Expedition 1", LocalDate.of(2000, 10, 31), "Completed", "First long-duration stay on the International Space Station");
        dao.insert(mission);
        Mission resultBeforeDelete = dao.findById(1L).get();

        dao.deleteById(1L);

        Mission resultAfterDelete = dao.findById(1L).orElse(null);

        Assertions.assertNotNull(resultBeforeDelete);
        assertNull(resultAfterDelete);
    }

    @Test
    void update() {
    }
}