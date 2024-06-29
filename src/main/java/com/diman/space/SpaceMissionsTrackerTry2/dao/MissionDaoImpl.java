package com.diman.space.SpaceMissionsTrackerTry2.dao;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class MissionDaoImpl implements MissionDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<Mission> findByNameContainingIgnoreCase(String name) {
        return null;
    }

    @Override
    public List<Mission> findByLaunchDate(LocalDate launchDate) {
        return null;
    }

    @Override
    public List<Mission> findAll() {
        String sql = "SELECT * FROM missions";

        return jdbcTemplate.query(sql, new MissionRowMapper());
    }

    @Override
    public Optional<Mission> findById(Long id) {
        String sql = "" +
                "SELECT * FROM missions " +
                "WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, new MissionRowMapper(), id));
    }

    @Override
    public void insert(Mission mission) {
        String sql = "" +
                "INSERT INTO missions " +
                "(name, launch_date, status, description) " +
                "VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, mission.getName(), mission.getLaunchDate(), mission.getStatus(), mission.getDescription());
    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void update() {

    }
}
