package com.scholarmate.dto;

import com.scholarmate.entity.StudentProfile;
import java.math.BigDecimal;

public record StudentProfileResponse(Long id, String fullName, String email, String course, String college,
                                     String educationLevel, BigDecimal academicMarks, BigDecimal familyIncome,
                                     String category, String state) {
    public static StudentProfileResponse from(StudentProfile profile) {
        return new StudentProfileResponse(profile.getId(), profile.getFullName(), profile.getEmail(), profile.getCourse(),
                profile.getCollege(), profile.getEducationLevel(), profile.getAcademicMarks(), profile.getFamilyIncome(),
                profile.getCategory(), profile.getState());
    }
}
