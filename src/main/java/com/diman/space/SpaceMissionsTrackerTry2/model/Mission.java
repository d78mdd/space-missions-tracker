package com.diman.space.SpaceMissionsTrackerTry2.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@Entity
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private LocalDate launchDate;
    private String status;
    private String description;

    public Mission() {

    }

    public Mission(String name, LocalDate launchDate, String status, String description) {
        this.name = name;
        this.launchDate = launchDate;
        this.status = status;
        this.description = description;
    }

    public Mission(Long id, String name, LocalDate launchDate, String status, String description) {
        this.id = id;
        this.name = name;
        this.launchDate = launchDate;
        this.status = status;
        this.description = description;
    }
}
