package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.request.TestimonialRequest;
import com.hdtlogistics.cms.entity.Testimonial;
import com.hdtlogistics.cms.service.TestimonialAdminService;
import com.hdtlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/testimonials")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
public class TestimonialAdminController {

    private final TestimonialAdminService testimonialAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Testimonial>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(testimonialAdminService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Testimonial>> create(@Valid @RequestBody TestimonialRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(testimonialAdminService.create(request), "Tạo đánh giá thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Testimonial>> update(
            @PathVariable UUID id, @Valid @RequestBody TestimonialRequest request) {
        return ResponseEntity.ok(ApiResponse.success(testimonialAdminService.update(id, request), "Cập nhật đánh giá thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        testimonialAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa đánh giá thành công"));
    }
}
