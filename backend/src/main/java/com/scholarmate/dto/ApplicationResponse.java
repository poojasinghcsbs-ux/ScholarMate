package com.scholarmate.dto;

import com.scholarmate.entity.ScholarshipApplication;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record ApplicationResponse(Long id, Long scholarshipId, String scholarshipTitle, String provider,
                                  LocalDate deadline, String status, LocalDateTime appliedAt,
                                  LocalDateTime updatedAt, String notes) {
    public static ApplicationResponse from(ScholarshipApplication application) {
        return new ApplicationResponse(application.getId(), application.getScholarship().getId(),
                application.getScholarship().getTitle(), application.getScholarship().getProvider(),
                application.getScholarship().getDeadline(), application.getStatus().name(), application.getAppliedAt(),
                application.getUpdatedAt(), application.getNotes());
    }
}
