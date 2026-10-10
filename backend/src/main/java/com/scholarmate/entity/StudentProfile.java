package com.scholarmate.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "student_profiles")
public class StudentProfile {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String fullName;
    @Column(nullable = false, unique = true) private String email;
    private String course;
    private String college;
    private String educationLevel;
    private BigDecimal academicMarks;
    private BigDecimal familyIncome;
    private String category;
    private String state;

    public Long getId() { return id; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getCourse() { return course; }
    public void setCourse(String course) { this.course = course; }
    public String getCollege() { return college; }
    public void setCollege(String college) { this.college = college; }
    public String getEducationLevel() { return educationLevel; }
    public void setEducationLevel(String educationLevel) { this.educationLevel = educationLevel; }
    public BigDecimal getAcademicMarks() { return academicMarks; }
    public void setAcademicMarks(BigDecimal academicMarks) { this.academicMarks = academicMarks; }
    public BigDecimal getFamilyIncome() { return familyIncome; }
    public void setFamilyIncome(BigDecimal familyIncome) { this.familyIncome = familyIncome; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getState() { return state; }
    public void setState(String state) { this.state = state; }
}
