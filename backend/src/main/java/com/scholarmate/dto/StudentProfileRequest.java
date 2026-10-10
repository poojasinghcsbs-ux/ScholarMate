package com.scholarmate.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

public record StudentProfileRequest(
        @NotBlank String fullName,
        @NotBlank @Email String email,
        String course,
        String college,
        String educationLevel,
        @PositiveOrZero BigDecimal academicMarks,
        @PositiveOrZero BigDecimal familyIncome,
        String category,
        String state
) { }
