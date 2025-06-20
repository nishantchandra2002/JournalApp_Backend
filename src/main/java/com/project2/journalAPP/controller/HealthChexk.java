package com.project2.journalAPP.controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HealthChexk {
    @GetMapping("/health-check")
    public String HealthChexk() {
        return "OK";
    }
}
