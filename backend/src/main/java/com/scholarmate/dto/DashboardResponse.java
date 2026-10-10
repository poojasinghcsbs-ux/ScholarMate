package com.scholarmate.dto;

import java.time.LocalDate;
import java.util.List;

public record DashboardResponse(long totalApplications, long pendingApplications, long submittedApplications,
                                long approvedApplications, long rejectedApplications,
                                List<UpcomingDeadline> upcomingDeadlines) {
    public record UpcomingDeadline(Long scholarshipId, String scholarshipTitle, LocalDate deadline, String status) { }
}
