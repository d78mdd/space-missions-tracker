package com.diman.space.SpaceMissionsTrackerTry2.dao;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MissionDaoTests {

    @Autowired
    private MissionDao dao;


    // TODO shorten lines / extract test data for missions as beforeTest


    @Test
    public void testFindByNameContainingIgnoreCase() {
    }

    @Test
    public void testFindByExactName() {
    }

    @Test
    public void testFindByLaunchDate() {
    }

    @Test
    public void testFindAll() {
    }

    @Test
    public void testFindById() {
        Mission mission = new Mission("Curiosity Rover", LocalDate.of(2011, 11, 26), "Ongoing", "Mars rover exploring the surface of Mars");
        dao.insert(mission);

        Mission result = dao.findById(1L).get();

        assertNotNull(result);
        assertEquals(mission.getName(), result.getName());
    }

    @Test
    public void testInsert() {
        Mission mission = new Mission("Hubble Space Telescope", LocalDate.of(1990, 4, 24), "Ongoing", "Orbiting telescope for astronomical observations");
        Mission resultBeforeInsert = dao.findById(1L).orElse(null);

        dao.insert(mission);

        Mission resultAfterInsert = dao.findById(1L).get();

        assertNull(resultBeforeInsert);
        assertNotNull(resultAfterInsert);
        assertEquals(mission.getName(), resultAfterInsert.getName());
    }

    @Test
    public void testDeleteById() {
        Mission mission = new Mission("ISS Expedition 1", LocalDate.of(2000, 10, 31), "Completed", "First long-duration stay on the International Space Station");
        dao.insert(mission);
        Mission resultBeforeDelete = dao.findById(1L).get();

        dao.deleteById(1L);

        Mission resultAfterDelete = dao.findById(1L).orElse(null);

        Assertions.assertNotNull(resultBeforeDelete);
        assertNull(resultAfterDelete);
    }

    @Test
    public void testUpdate() {
        Mission mission = new Mission("Mars 2020 Perseverance", LocalDate.of(2020, 7, 30), "Ongoing", "Mars rover mission to seek signs of ancient life and collect samples");
        dao.insert(mission);
        Mission resultBeforeUpdate = dao.findById(1L).get();

        Mission missionUpdate = new Mission(1L, "Mars 2020 Perseverance", LocalDate.of(2020, 7, 30), "Ongoing", "Mars rover mission to seek signs of ancient life and collect samples, updated");
        dao.update(missionUpdate);

        Mission resultAfterUpdate = dao.findById(1L).get();

        Assertions.assertNotNull(resultBeforeUpdate);
        Assertions.assertNotNull(resultAfterUpdate);
        assertNotEquals(resultBeforeUpdate.getDescription(), resultAfterUpdate.getDescription());
    }
}