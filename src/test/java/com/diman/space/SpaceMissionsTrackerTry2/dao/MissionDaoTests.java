package com.diman.space.SpaceMissionsTrackerTry2.dao;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class MissionDaoTests {

    @Autowired
    private MissionDao dao;


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
        Mission mission = new Mission(1L, "Curiosity Rover", LocalDate.of(2011, 11, 26), "Ongoing", "Mars rover exploring the surface of Mars");
        dao.insert(mission);

        Mission result = dao.findById(1L).get();

        assertNotNull(result);
        assertEquals(mission.getName(), result.getName());
    }

    @Test
    void insert() {
    }

    @Test
    void deleteById() {
    }

    @Test
    void update() {
    }
}