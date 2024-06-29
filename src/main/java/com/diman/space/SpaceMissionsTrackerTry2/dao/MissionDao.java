package com.diman.space.SpaceMissionsTrackerTry2.dao;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MissionDao {

    List<Mission> findByNameContainingIgnoreCase(String name);

    List<Mission> findByExactName(String name);

    List<Mission> findByLaunchDate(LocalDate launchDate);

    List<Mission> findAll();

    Optional<Mission> findById(Long id);

    void insert(Mission mission);

    void deleteById(Long id);

    void update();
}

