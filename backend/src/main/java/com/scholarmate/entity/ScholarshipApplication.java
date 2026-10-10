package com.scholarmate.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "scholarship_applications", uniqueConstraints = @UniqueConstraint(columnNames = {"student_id", "scholarship_id"}))
public class ScholarshipApplication {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false) @JoinColumn(name = "student_id") private StudentProfile student;
    @ManyToOne(optional = false) @JoinColumn(name = "scholarship_id") private Scholarship scholarship;
    @Enumerated(EnumType.STRING) @Column(nullable = false) private ApplicationStatus status = ApplicationStatus.PENDING;
    @Column(nullable = false, updatable = false) private LocalDateTime appliedAt;
    private LocalDateTime updatedAt;
    @Column(length = 1000) private String notes;

    @PrePersist void onCreate() { appliedAt = LocalDateTime.now(); updatedAt = appliedAt; }
    @PreUpdate void onUpdate() { updatedAt = LocalDateTime.now(); }
    public Long getId() { return id; }
    public StudentProfile getStudent() { return student; }
    public void setStudent(StudentProfile student) { this.student = student; }
    public Scholarship getScholarship() { return scholarship; }
    public void setScholarship(Scholarship scholarship) { this.scholarship = scholarship; }
    public ApplicationStatus getStatus() { return status; }
    public void setStatus(ApplicationStatus status) { this.status = status; }
    public LocalDateTime getAppliedAt() { return appliedAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
