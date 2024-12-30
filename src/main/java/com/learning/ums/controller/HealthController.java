package com.learning.ums.controller;

import com.learning.ums.dto.ServiceHealthResponse;
import jakarta.annotation.PostConstruct;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
@RequestMapping("/health")
public class HealthController {

    public Instant serviceStartTime;

    @PostConstruct
    public void init() {

        serviceStartTime = Instant.now();
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ServiceHealthResponse> getServiceHealth() {

        ServiceHealthResponse serviceHealthResponse = new ServiceHealthResponse(
                "Service is up and running", String.valueOf(serviceStartTime),
                String.valueOf(Instant.now()));

        return ResponseEntity.ok(serviceHealthResponse);
    }
}
