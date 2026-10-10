package com.scholarmate.service;

import com.scholarmate.dto.DashboardResponse;
import com.scholarmate.entity.ApplicationStatus;
import com.scholarmate.repository.ScholarshipApplicationRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DashboardService {
    private final ScholarshipApplicationRepository applications;
    private final StudentProfileService students;
    public DashboardService(ScholarshipApplicationRepository applications, StudentProfileService students) {
        this.applications = applications; this.students = students;
    }
    public DashboardResponse get(Long studentId) {
        students.find(studentId);
        var deadlines = applications.findByStudentIdAndScholarshipDeadlineGreaterThanEqualOrderByScholarshipDeadlineAsc(studentId, LocalDate.now())
                .stream().limit(5).map(application -> new DashboardResponse.UpcomingDeadline(
                        application.getScholarship().getId(), application.getScholarship().getTitle(),
                        application.getScholarship().getDeadline(), application.getStatus().name())).toList();
        return new DashboardResponse(
                applications.countByStudentId(studentId),
                applications.countByStudentIdAndStatus(studentId, ApplicationStatus.PENDING),
                applications.countByStudentIdAndStatus(studentId, ApplicationStatus.SUBMITTED),
                applications.countByStudentIdAndStatus(studentId, ApplicationStatus.APPROVED),
                applications.countByStudentIdAndStatus(studentId, ApplicationStatus.REJECTED),
                deadlines);
    }
}
