package com.example.springbootapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class IntegrationTestStartup implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) {
        log.info("¯\\_(ツ)_/¯ Integration test startup");
    }
}
