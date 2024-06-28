package com.diman.space.SpaceMissionsTrackerTry2.repository;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    List<Mission> findByNameContainingIgnoreCase(String name);

    List<Mission> findByLaunchDate(LocalDate launchDate);
}

