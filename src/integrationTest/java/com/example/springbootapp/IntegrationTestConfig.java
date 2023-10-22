package com.example.springbootapp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
@Slf4j
public class IntegrationTestConfig {

    @EventListener(ApplicationReadyEvent.class)
    public void appReadyStartup() {
        log.info("¯\\_(ツ)_/¯ Integration test application is ready");
    }
}

