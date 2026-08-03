package com.alumni.controller;

import com.alumni.dto.DashboardStatsDTO;
import com.alumni.service.StatsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatsController {

    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping("/dashboard")
    public DashboardStatsDTO getDashboardStats() {
        return statsService.getDashboardStats();
    }
}