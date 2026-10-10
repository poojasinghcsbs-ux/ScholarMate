package com.scholarmate.service;

import com.scholarmate.dto.StudentProfileRequest;
import com.scholarmate.dto.StudentProfileResponse;
import com.scholarmate.entity.StudentProfile;
import com.scholarmate.exception.ConflictException;
import com.scholarmate.exception.ResourceNotFoundException;
import com.scholarmate.repository.StudentProfileRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentProfileService {
    private final StudentProfileRepository repository;
    public StudentProfileService(StudentProfileRepository repository) { this.repository = repository; }

    public StudentProfileResponse create(StudentProfileRequest request) {
        repository.findByEmailIgnoreCase(request.email()).ifPresent(profile -> { throw new ConflictException("A profile already exists for this email."); });
        StudentProfile profile = new StudentProfile();
        apply(profile, request);
        return StudentProfileResponse.from(repository.save(profile));
    }
    public StudentProfileResponse get(Long id) { return StudentProfileResponse.from(find(id)); }
    public StudentProfileResponse update(Long id, StudentProfileRequest request) {
        StudentProfile profile = find(id);
        repository.findByEmailIgnoreCase(request.email()).filter(other -> !other.getId().equals(id))
                .ifPresent(other -> { throw new ConflictException("This email belongs to another student profile."); });
        apply(profile, request);
        return StudentProfileResponse.from(repository.save(profile));
    }
    public StudentProfile find(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Student profile not found.")); }
    private void apply(StudentProfile profile, StudentProfileRequest request) {
        profile.setFullName(request.fullName()); profile.setEmail(request.email().trim().toLowerCase());
        profile.setCourse(request.course()); profile.setCollege(request.college()); profile.setEducationLevel(request.educationLevel());
        profile.setAcademicMarks(request.academicMarks()); profile.setFamilyIncome(request.familyIncome());
        profile.setCategory(request.category()); profile.setState(request.state());
    }
}
