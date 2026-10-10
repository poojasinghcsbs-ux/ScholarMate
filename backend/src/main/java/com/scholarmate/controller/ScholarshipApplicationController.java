package com.scholarmate.controller;

import com.scholarmate.dto.ApplicationResponse;
import com.scholarmate.dto.CreateApplicationRequest;
import com.scholarmate.dto.UpdateApplicationStatusRequest;
import com.scholarmate.service.ScholarshipApplicationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ScholarshipApplicationController {
    private final ScholarshipApplicationService service;
    public ScholarshipApplicationController(ScholarshipApplicationService service) { this.service = service; }
    @PostMapping("/api/students/{studentId}/applications") @ResponseStatus(HttpStatus.CREATED)
    public ApplicationResponse create(@PathVariable Long studentId, @Valid @RequestBody CreateApplicationRequest request) {
        return service.create(studentId, request);
    }
    @GetMapping("/api/students/{studentId}/applications")
    public List<ApplicationResponse> list(@PathVariable Long studentId) { return service.listForStudent(studentId); }
    @PatchMapping("/api/applications/{applicationId}/status")
    public ApplicationResponse updateStatus(@PathVariable Long applicationId, @Valid @RequestBody UpdateApplicationStatusRequest request) {
        return service.updateStatus(applicationId, request.status());
    }
}
