package com.scholarmate.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "scholarships")
public class Scholarship {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false) private String title;
    @Column(nullable = false) private String provider;
    @Column(length = 3000) private String description;
    @Column(length = 3000) private String eligibilityCriteria;
    private BigDecimal amount;
    private LocalDate deadline;
    private String officialApplicationLink;
    private String educationLevel;
    private String category;
    private BigDecimal maxFamilyIncome;
    private boolean active = true;

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getEligibilityCriteria() { return eligibilityCriteria; }
    public void setEligibilityCriteria(String eligibilityCriteria) { this.eligibilityCriteria = eligibilityCriteria; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public LocalDate getDeadline() { return deadline; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }
    public String getOfficialApplicationLink() { return officialApplicationLink; }
    public void setOfficialApplicationLink(String officialApplicationLink) { this.officialApplicationLink = officialApplicationLink; }
    public String getEducationLevel() { return educationLevel; }
    public void setEducationLevel(String educationLevel) { this.educationLevel = educationLevel; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public BigDecimal getMaxFamilyIncome() { return maxFamilyIncome; }
    public void setMaxFamilyIncome(BigDecimal maxFamilyIncome) { this.maxFamilyIncome = maxFamilyIncome; }
    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
