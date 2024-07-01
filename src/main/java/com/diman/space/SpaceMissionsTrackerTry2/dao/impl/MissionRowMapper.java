package com.diman.space.SpaceMissionsTrackerTry2.dao.impl;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class MissionRowMapper implements RowMapper<Mission> {

    @Override
    public Mission mapRow(ResultSet rs, int rowNum) throws SQLException {
        Mission mission = new Mission();

        mission.setId(rs.getLong("id"));
        mission.setName(rs.getString("name"));
        mission.setLaunchDate(rs.getObject("launch_date", LocalDate.class));
        mission.setStatus(rs.getString("status"));
        mission.setDescription(rs.getString("description"));

        return mission;
    }
}
