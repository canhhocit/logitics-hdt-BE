package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.entity.*;
import com.hdtlogistics.cms.service.CmsPublicService;
import com.hdtlogistics.common.dto.ApiResponse;
import com.hdtlogistics.lead.entity.ContactPerson;
import com.hdtlogistics.lead.entity.Office;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/public")
@RequiredArgsConstructor
public class CmsPublicController {

    private final CmsPublicService cmsPublicService;

    // ─────────────────────────────────────────────
    // Services
    // ─────────────────────────────────────────────

    @GetMapping("/services")
    public ResponseEntity<ApiResponse<List<LogisticsService>>> getServices() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicServices()));
    }

    @GetMapping("/services/{slug}")
    public ResponseEntity<ApiResponse<LogisticsService>> getServiceBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getServiceBySlug(slug)));
    }

    // ─────────────────────────────────────────────
    // Menu
    // ─────────────────────────────────────────────

    @GetMapping("/menus")
    public ResponseEntity<ApiResponse<List<MenuItem>>> getMenu(
            @RequestParam(defaultValue = "MAIN") String position) {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getMenuByPosition(position)));
    }

    // ─────────────────────────────────────────────
    // Site Settings
    // ─────────────────────────────────────────────

    @GetMapping("/settings")
    public ResponseEntity<ApiResponse<Map<String, String>>> getSettings() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicSettings()));
    }

    // ─────────────────────────────────────────────
    // Posts & Categories
    // ─────────────────────────────────────────────

    @GetMapping("/posts")
    public ResponseEntity<ApiResponse<Page<Post>>> getPosts(
            @RequestParam(required = false) String categorySlug,
            @PageableDefault(size = 10, sort = "publishedAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<Post> page = cmsPublicService.getPublicPostsPaged(categorySlug, pageable);
        return ResponseEntity.ok(ApiResponse.success(page));
    }

    @GetMapping("/posts/{slug}")
    public ResponseEntity<ApiResponse<Post>> getPostBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicPostBySlug(slug)));
    }

    @GetMapping("/categories")
    public ResponseEntity<ApiResponse<List<Category>>> getCategories() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicCategories()));
    }

    // ─────────────────────────────────────────────
    // Partners & Testimonials
    // ─────────────────────────────────────────────

    @GetMapping("/partners")
    public ResponseEntity<ApiResponse<List<Partner>>> getPartners(
            @RequestParam(required = false) String type) {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPartners(type)));
    }

    @GetMapping("/testimonials")
    public ResponseEntity<ApiResponse<List<Testimonial>>> getTestimonials() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getTestimonials()));
    }

    // ─────────────────────────────────────────────
    // Offices & Contacts  [MỚI]
    // ─────────────────────────────────────────────

    @GetMapping("/offices")
    public ResponseEntity<ApiResponse<List<Office>>> getOffices(
            @RequestParam(required = false) String region) {
        if (region != null && !region.isBlank()) {
            return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicOfficesByRegion(region)));
        }
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicOffices()));
    }

    @GetMapping("/contacts")
    public ResponseEntity<ApiResponse<List<ContactPerson>>> getContacts() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicContacts()));
    }

    // ─────────────────────────────────────────────
    // Job Postings  [MỚI]
    // ─────────────────────────────────────────────

    @GetMapping("/jobs")
    public ResponseEntity<ApiResponse<List<JobPosting>>> getJobs() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicJobs()));
    }

    @GetMapping("/jobs/{id}")
    public ResponseEntity<ApiResponse<JobPosting>> getJobById(@PathVariable UUID id) {
        return cmsPublicService.getPublicJobById(id)
                .map(job -> ResponseEntity.ok(ApiResponse.success(job)))
                .orElse(ResponseEntity.notFound().build());
    }

    // ─────────────────────────────────────────────
    // Site Awards  [MỚI]
    // ─────────────────────────────────────────────

    @GetMapping("/awards")
    public ResponseEntity<ApiResponse<List<SiteAward>>> getAwards() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicAwards()));
    }

    // ─────────────────────────────────────────────
    // Company Values  [MỚI]
    // ─────────────────────────────────────────────

    @GetMapping("/company-values")
    public ResponseEntity<ApiResponse<List<CompanyValue>>> getCompanyValues() {
        return ResponseEntity.ok(ApiResponse.success(cmsPublicService.getPublicCompanyValues()));
    }
}
