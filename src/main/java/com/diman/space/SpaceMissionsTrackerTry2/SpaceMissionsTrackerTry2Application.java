package com.diman.space.SpaceMissionsTrackerTry2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
https://chatgpt.com/c/5f830d40-1bc2-4a68-a730-552a8dc9a203 */
@SpringBootApplication
public class SpaceMissionsTrackerTry2Application {

    public static void main(String[] args) throws IOException {
        ConfigurableApplicationContext context = SpringApplication.run(SpaceMissionsTrackerTry2Application.class, args);

        JdbcTemplate jdbcTemplate = context.getBean(JdbcTemplate.class);

        String sql = readFileToString("src/main/resources/sample-data.sql");

        jdbcTemplate.execute(sql);

    }

    private static String readFileToString(String filePath) throws IOException {
        try (Stream<String> stream = Files.lines(Paths.get(filePath))) {
            return stream.collect(Collectors.joining("\n"));
        }
    }


    /*
    TODO use template engine thymeleaf
    TODO add variant - instead of H2 in-mem DB use stored DB
    TODO use swagger
    TODO use DTO and mapping
    TODO validations
    TODO add controller tests without mocks
    TODO consider adding @ParameterizedTests
    TODO add support for more than 1 DAO implementation - 1 JPA and 1 JDBC?
    TODO add support for more than 1 Service implementation - ?
    TODO add support for more than 1 Controller implementation - 1 RestAPI and 1 Thymeleaf?
    TODO add variant with xml based configuration?

    TODO use more conscious way for mockMvc.perform(post("/api/missions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content
        for the next project?
    TODO use more conscious way for andExpect(jsonPath())
        for the next project?
     */
}
