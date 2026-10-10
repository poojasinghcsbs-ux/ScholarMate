package com.scholarmate.dto;

import jakarta.validation.constraints.NotNull;

public record CreateApplicationRequest(@NotNull Long scholarshipId, String notes) { }
