package com.scholarmate.repository;

import com.scholarmate.entity.ApplicationStatus;
import com.scholarmate.entity.ScholarshipApplication;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ScholarshipApplicationRepository extends JpaRepository<ScholarshipApplication, Long> {
    List<ScholarshipApplication> findByStudentIdOrderByAppliedAtDesc(Long studentId);
    Optional<ScholarshipApplication> findByStudentIdAndScholarshipId(Long studentId, Long scholarshipId);
    long countByStudentId(Long studentId);
    long countByStudentIdAndStatus(Long studentId, ApplicationStatus status);
    List<ScholarshipApplication> findByStudentIdAndScholarshipDeadlineGreaterThanEqualOrderByScholarshipDeadlineAsc(Long studentId, LocalDate date);
}
