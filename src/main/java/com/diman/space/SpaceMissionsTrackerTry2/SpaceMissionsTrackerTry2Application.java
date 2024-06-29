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
    TODO implement tests in Java with junit/testng/spring test mvc
    TODO use template engine thymeleaf
    TODO instead of JPA use JDBC
    TODO instead of H2 in-mem DB use stored DB
    TODO use swagger
    TODO use DTO and mapping
    TODO validations
    TODO use interfaces and implementation classes for the repository and service
    TODO add Postman query for search mission by name and search mission by date
    TODO add variant using Gradle instead of Maven

    TODO use more conscious way for mockMvc.perform(post("/api/missions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content
        for the next project?
    TODO use more conscious way for andExpect(jsonPath())
        for the next project?
     */
}
