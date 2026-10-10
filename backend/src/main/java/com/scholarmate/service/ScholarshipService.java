package com.scholarmate.service;

import com.scholarmate.dto.ScholarshipResponse;
import com.scholarmate.entity.Scholarship;
import com.scholarmate.exception.ResourceNotFoundException;
import com.scholarmate.repository.ScholarshipRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ScholarshipService {
    private final ScholarshipRepository repository;
    public ScholarshipService(ScholarshipRepository repository) { this.repository = repository; }
    public List<ScholarshipResponse> search(String query, String educationLevel, String category, BigDecimal maxFamilyIncome) {
        Specification<Scholarship> spec = (root, q, cb) -> cb.isTrue(root.get("active"));
        if (query != null && !query.isBlank()) {
            String term = "%" + query.toLowerCase() + "%";
            spec = spec.and((root, q, cb) -> cb.or(cb.like(cb.lower(root.get("title")), term), cb.like(cb.lower(root.get("provider")), term)));
        }
        if (educationLevel != null && !educationLevel.isBlank()) spec = spec.and((root, q, cb) -> cb.equal(root.get("educationLevel"), educationLevel));
        if (category != null && !category.isBlank()) spec = spec.and((root, q, cb) -> cb.equal(root.get("category"), category));
        if (maxFamilyIncome != null) spec = spec.and((root, q, cb) -> cb.or(cb.isNull(root.get("maxFamilyIncome")), cb.greaterThanOrEqualTo(root.get("maxFamilyIncome"), maxFamilyIncome)));
        spec = spec.and((root, q, cb) -> cb.or(cb.isNull(root.get("deadline")), cb.greaterThanOrEqualTo(root.get("deadline"), LocalDate.now())));
        return repository.findAll(spec).stream().map(ScholarshipResponse::from).toList();
    }
    public ScholarshipResponse get(Long id) { return ScholarshipResponse.from(find(id)); }
    public Scholarship find(Long id) { return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Scholarship not found.")); }
}
