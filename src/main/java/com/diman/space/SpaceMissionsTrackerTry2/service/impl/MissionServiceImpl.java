package com.diman.space.SpaceMissionsTrackerTry2.service.impl;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import com.diman.space.SpaceMissionsTrackerTry2.repository.MissionRepository;
import com.diman.space.SpaceMissionsTrackerTry2.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {

    private MissionRepository missionRepository;

    @Autowired
    public MissionServiceImpl(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    @Override
    public List<Mission> getAllMissions() {
        return missionRepository.findAll();
    }

    @Override
    public List<Mission> searchMissionsByName(String name) {
        return missionRepository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Mission> searchMissionsByDate(LocalDate date) {
        return missionRepository.findByLaunchDate(date);
    }

    @Override
    public Mission getMissionById(Long id) {
        return missionRepository.findById(id).orElse(null);
    }

    @Override
    public Mission saveMission(Mission mission) {
        return missionRepository.save(mission);
    }

    @Override
    public void deleteMission(Long id) {
        missionRepository.deleteById(id);
    }

}
