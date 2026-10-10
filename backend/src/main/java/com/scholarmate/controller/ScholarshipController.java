package com.scholarmate.controller;

import com.scholarmate.dto.ScholarshipResponse;
import com.scholarmate.service.ScholarshipService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/scholarships")
public class ScholarshipController {
    private final ScholarshipService service;
    public ScholarshipController(ScholarshipService service) { this.service = service; }
    @GetMapping
    public List<ScholarshipResponse> search(@RequestParam(required = false) String query,
                                            @RequestParam(required = false) String educationLevel,
                                            @RequestParam(required = false) String category,
                                            @RequestParam(required = false) BigDecimal maxFamilyIncome) {
        return service.search(query, educationLevel, category, maxFamilyIncome);
    }
    @GetMapping("/{scholarshipId}")
    public ScholarshipResponse get(@PathVariable Long scholarshipId) { return service.get(scholarshipId); }
}
