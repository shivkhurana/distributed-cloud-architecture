package com.example.routing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the Enterprise Microservice Routing application.
 * <p>
 * This Spring Boot application provides a core routing service that
 * simulates high-volume transaction routing and exposes REST endpoints
 * for processing and retrieving routed transactions.
 */
@SpringBootApplication
public class Application {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args runtime arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
