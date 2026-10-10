package com.scholarmate.controller;

import com.scholarmate.dto.DashboardResponse;
import com.scholarmate.service.DashboardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/students/{studentId}/dashboard")
public class DashboardController {
    private final DashboardService service;
    public DashboardController(DashboardService service) { this.service = service; }
    @GetMapping
    public DashboardResponse get(@PathVariable Long studentId) { return service.get(studentId); }
}
