package com.diman.space.SpaceMissionsTrackerTry2.controller;

import com.diman.space.SpaceMissionsTrackerTry2.model.Mission;
import com.diman.space.SpaceMissionsTrackerTry2.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    private MissionService missionService;

    @Autowired
    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping()
    public List<Mission> getAllMissions() {
        return missionService.getAllMissions();
    }

    @GetMapping("/search")
    public List<Mission> searchMissions(@RequestParam(required = false) String name,
                                        @RequestParam(required = false) LocalDate date) {
        if (name != null) {
            return missionService.searchMissionsByName(name);
        } else if (date != null) {
            return missionService.searchMissionsByDate(date);
        } else {
            return missionService.getAllMissions();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mission> getMission(@PathVariable Long id) {
        Mission mission = missionService.getMissionById(id);
        if (mission == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(mission, HttpStatus.OK);
        }
    }

    @PostMapping
    public ResponseEntity<Mission> createMission(@RequestBody Mission mission) {
        Mission missionSaved = missionService.saveMission(mission);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/missions/1"); // TODO remove hardcoded id

        return new ResponseEntity<>(missionSaved, headers, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public Mission updateMission(@PathVariable Long id, @RequestBody Mission mission) {
        mission.setId(id);
        return missionService.saveMission(mission);
    }

    @DeleteMapping("/{id}")
    public void deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
    }
}
