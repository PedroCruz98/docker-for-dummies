package com.example.events4all;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories(basePackages = "com.example.events4all.events.persistence")
public class EventsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventsApplication.class, args);
    }

}
