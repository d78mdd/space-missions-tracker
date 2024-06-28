package com.diman.space.SpaceMissionsTrackerTry2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MissionService {

    @Autowired
    private MissionRepository missionRepository;

    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    public List<Mission> searchMissionsByName(String name) {
        return missionRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Mission> searchMissionsByDate(LocalDate date) {
        return missionRepository.findByLaunchDate(date);
    }

    public Mission getMissionById(Long id) {
        return missionRepository.findById(id).orElse(null);
    }

    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }

}
