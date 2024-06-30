package com.diman.space.SpaceMissionsTrackerTry2.service.impl;

import com.diman.space.SpaceMissionsTrackerTry2.dao.MissionDao;
import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import com.diman.space.SpaceMissionsTrackerTry2.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class MissionServiceImpl implements MissionService {

    private MissionDao missionDao;

    @Autowired
    public MissionServiceImpl(MissionDao missionDao) {
        this.missionDao = missionDao;
    }

    @Override
    public List<Mission> getAllMissions() {
        return missionDao.findAll();
    }

    @Override
    public List<Mission> searchMissionsByName(String name) {
        return missionDao.findByExactName(name);
    }

    @Override
    public List<Mission> searchMissionsByDate(LocalDate date) {
        return missionDao.findByLaunchDate(date);
    }

    @Override
    public Mission getMissionById(Long id) {
        return missionDao.findById(id).orElse(null);
    }

    @Override
    public void saveMission(Mission mission) {
        missionDao.insert(mission);
    }

    @Override
    public void updateMission(Mission mission) {
        missionDao.update(mission);
    }

    @Override
    public void deleteMission(Long id) {
        missionDao.deleteById(id);
    }

}
