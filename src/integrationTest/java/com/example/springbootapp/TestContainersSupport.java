package com.example.springbootapp;

import org.springframework.context.annotation.Configuration;
import org.testcontainers.containers.DockerComposeContainer;

import java.io.File;
import java.time.Duration;

import static java.lang.ClassLoader.getSystemResource;
import static org.testcontainers.containers.wait.strategy.Wait.forListeningPort;
import static org.testcontainers.containers.wait.strategy.Wait.forLogMessage;

// @Configuration ensures spring context will wait for TestContainersSupport to complete
@Configuration
public class TestContainersSupport {

    private static final String DOCKER_COMPOSE_YML = "docker-compose.yml";
    private static final String DB_SERVICE = "db";
    private static final Duration TIMEOUT = Duration.ofMinutes(5);

    static {
        new DockerComposeContainer<>(new File(getSystemResource(DOCKER_COMPOSE_YML).getFile()))
                .withExposedService(DB_SERVICE, 3306)
                //.waitingFor(DB_SERVICE, forLogMessage(".+ready for connections.+", 1).withStartupTimeout(TIMEOUT))
                .waitingFor(DB_SERVICE, forListeningPort().withStartupTimeout(TIMEOUT))
                .start();
    }

}