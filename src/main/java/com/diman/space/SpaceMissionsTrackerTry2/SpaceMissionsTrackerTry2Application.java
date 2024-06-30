package com.diman.space.SpaceMissionsTrackerTry2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
https://chatgpt.com/c/5f830d40-1bc2-4a68-a730-552a8dc9a203 */
@SpringBootApplication
public class SpaceMissionsTrackerTry2Application {

    public static void main(String[] args) {
        SpringApplication.run(SpaceMissionsTrackerTry2Application.class, args);
    }

    /*
    TODO add variant - instead of H2 in-mem DB use stored DB
    TODO consider adding @ParameterizedTests in DAO
     */
}
