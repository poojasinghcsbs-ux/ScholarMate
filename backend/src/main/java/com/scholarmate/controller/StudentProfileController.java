package com.scholarmate.controller;

import com.scholarmate.dto.StudentProfileRequest;
import com.scholarmate.dto.StudentProfileResponse;
import com.scholarmate.service.StudentProfileService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/students")
public class StudentProfileController {
    private final StudentProfileService service;
    public StudentProfileController(StudentProfileService service) { this.service = service; }
    @PostMapping("/profile") @ResponseStatus(HttpStatus.CREATED)
    public StudentProfileResponse create(@Valid @RequestBody StudentProfileRequest request) { return service.create(request); }
    @GetMapping("/{studentId}/profile")
    public StudentProfileResponse get(@PathVariable Long studentId) { return service.get(studentId); }
    @PutMapping("/{studentId}/profile")
    public StudentProfileResponse update(@PathVariable Long studentId, @Valid @RequestBody StudentProfileRequest request) { return service.update(studentId, request); }
}
