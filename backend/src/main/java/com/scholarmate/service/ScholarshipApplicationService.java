package com.scholarmate.service;

import com.scholarmate.dto.ApplicationResponse;
import com.scholarmate.dto.CreateApplicationRequest;
import com.scholarmate.entity.ApplicationStatus;
import com.scholarmate.entity.ScholarshipApplication;
import com.scholarmate.exception.ConflictException;
import com.scholarmate.exception.ResourceNotFoundException;
import com.scholarmate.repository.ScholarshipApplicationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScholarshipApplicationService {
    private final ScholarshipApplicationRepository repository;
    private final StudentProfileService studentProfileService;
    private final ScholarshipService scholarshipService;
    public ScholarshipApplicationService(ScholarshipApplicationRepository repository, StudentProfileService studentProfileService, ScholarshipService scholarshipService) {
        this.repository = repository; this.studentProfileService = studentProfileService; this.scholarshipService = scholarshipService;
    }
    public ApplicationResponse create(Long studentId, CreateApplicationRequest request) {
        repository.findByStudentIdAndScholarshipId(studentId, request.scholarshipId())
                .ifPresent(existing -> { throw new ConflictException("An application already exists for this scholarship."); });
        ScholarshipApplication application = new ScholarshipApplication();
        application.setStudent(studentProfileService.find(studentId));
        application.setScholarship(scholarshipService.find(request.scholarshipId()));
        application.setNotes(request.notes());
        application.setStatus(ApplicationStatus.PENDING);
        return ApplicationResponse.from(repository.save(application));
    }
    public List<ApplicationResponse> listForStudent(Long studentId) {
        studentProfileService.find(studentId);
        return repository.findByStudentIdOrderByAppliedAtDesc(studentId).stream().map(ApplicationResponse::from).toList();
    }
    public ApplicationResponse updateStatus(Long applicationId, ApplicationStatus status) {
        ScholarshipApplication application = repository.findById(applicationId)
                .orElseThrow(() -> new ResourceNotFoundException("Application not found."));
        application.setStatus(status);
        return ApplicationResponse.from(repository.save(application));
    }
}
