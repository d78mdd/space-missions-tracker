package com.diman.space.SpaceMissionsTrackerTry2.dao;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Stream;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest
@Sql(value = "/schema.sql", executionPhase = AFTER_TEST_METHOD)
public class MissionDaoTests {

    @Autowired
    private MissionDao dao;

    // TODO shorten lines / extract test data for missions as beforeTest or in a data.sql in test resources , no

    static Stream<Arguments> arguments() {
        return Stream.of(
                Arguments.of(new Mission("Pioneer 10", LocalDate.of(1972, 3, 2), "Completed", "First spacecraft to travel through the asterod belt and make a flyby of Jupiter")),
                Arguments.of(new Mission("", LocalDate.of(1972, 3, 2), "Completed", "First spacecraft to travel through the asterod belt and make a flyby of Jupiter")),
                Arguments.of(new Mission("Pioneer 10", LocalDate.of(1972, 3, 2), "", "First spacecraft to travel through the asterod belt and make a flyby of Jupiter")),
                Arguments.of(new Mission("Pioneer 10", LocalDate.of(1972, 3, 2), "Completed", ""))
                );
    }


    @Test
    public void testFindByNameContainingIgnoreCase() {
    }

    @ParameterizedTest()
    @MethodSource("arguments")
    public void testWithVariousData(Mission mission) {
        assertDoesNotThrow(() -> dao.insert(mission));
    }

    @Test
    public void testFindByExactName() {
        Mission mission1 = new Mission("Pioneer 10", LocalDate.of(1972, 3, 2), "Completed", "First spacecraft to travel through the asterod belt and make a flyby of Jupiter");
        Mission mission2 = new Mission("Cassini-Huygens", LocalDate.of(1997, 10, 15), "Completed", "Study of Saturn and its moons, including the Huygens probe landing on Titan");
        List<Mission> resultBeforeInsert = dao.findAll();
        dao.insert(mission1);
        dao.insert(mission2);

        List<Mission> result = dao.findByExactName("Cassini-Huygens");

        assertEquals(0, resultBeforeInsert.size());
        assertEquals(1, result.size());
    }

    @Test
    public void testFindByLaunchDate() {
        Mission mission1 = new Mission("New Horizons", LocalDate.of(2006, 1, 9), "Ongoing", "Flyby study of Pluto and the Kuiper Belt");
        Mission mission2 = new Mission("Appollo 13", LocalDate.of(1970, 4, 11), "Aborted", "Third crewed mission to land on the Moon, aborted due to and oxygen tank explosion");
        List<Mission> resultBeforeInsert = dao.findAll();
        dao.insert(mission1);
        dao.insert(mission2);

        List<Mission> result = dao.findByLaunchDate(LocalDate.of(2006, 1, 9));

        assertEquals(0, resultBeforeInsert.size());
        assertEquals(1, result.size());
    }

    @Test
    public void testFindAll() {
        Mission mission1 = new Mission("Mars 2020 Perseverance", LocalDate.of(2020, 7, 30), "Ongoing", "Mars rover mission to seek signs of ancient life and collect samples");
        Mission mission2 = new Mission("Galileo", LocalDate.of(1989, 10, 18), "Completed", "Study of Jupiter and its moons");
        List<Mission> resultBeforeInsert = dao.findAll();
        dao.insert(mission1);
        dao.insert(mission2);

        List<Mission> resultAfterInsert = dao.findAll();

        assertEquals(0, resultBeforeInsert.size());
        assertEquals(2, resultAfterInsert.size());
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
