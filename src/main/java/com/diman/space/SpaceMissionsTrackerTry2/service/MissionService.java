package com.diman.space.SpaceMissionsTrackerTry2.service;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;

import java.time.LocalDate;
import java.util.List;

public interface MissionService {

    List<Mission> getAllMissions();

    List<Mission> searchMissionsByName(String name);

    List<Mission> searchMissionsByDate(LocalDate date);

    Mission getMissionById(Long id);

    Mission saveMission(Mission mission);

    void deleteMission(Long id);

}
