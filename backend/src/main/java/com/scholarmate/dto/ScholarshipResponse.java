package com.scholarmate.dto;

import com.scholarmate.entity.Scholarship;
import java.math.BigDecimal;
import java.time.LocalDate;

public record ScholarshipResponse(Long id, String title, String provider, String description, String eligibilityCriteria,
                                  BigDecimal amount, LocalDate deadline, String officialApplicationLink,
                                  String educationLevel, String category, BigDecimal maxFamilyIncome, boolean active) {
    public static ScholarshipResponse from(Scholarship scholarship) {
        return new ScholarshipResponse(scholarship.getId(), scholarship.getTitle(), scholarship.getProvider(),
                scholarship.getDescription(), scholarship.getEligibilityCriteria(), scholarship.getAmount(), scholarship.getDeadline(),
                scholarship.getOfficialApplicationLink(), scholarship.getEducationLevel(), scholarship.getCategory(),
                scholarship.getMaxFamilyIncome(), scholarship.isActive());
    }
}
