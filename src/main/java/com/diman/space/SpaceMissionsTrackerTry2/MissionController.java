package com.diman.space.SpaceMissionsTrackerTry2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
public class MissionController {

    @Autowired
    private MissionService missionService;

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
    public Mission getMission(@PathVariable Long id) {
        return missionService.getMissionById(id);
    }

    @PostMapping
    public Mission createMission(@RequestBody Mission mission) {
        return missionService.saveMission(mission);
    }

    @PutMapping("/{id}")
    public Mission createMission(@PathVariable Long id, @RequestBody Mission mission) {
        mission.setId(id);
        return missionService.saveMission(mission);
    }

    @DeleteMapping("/{id}")
    public void deleteMission(@PathVariable Long id) {
        missionService.deleteMission(id);
    }
}
