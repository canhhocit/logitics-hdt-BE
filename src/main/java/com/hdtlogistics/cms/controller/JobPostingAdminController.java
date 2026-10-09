package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.request.JobPostingRequest;
import com.hdtlogistics.cms.entity.JobPosting;
import com.hdtlogistics.cms.service.JobPostingAdminService;
import com.hdtlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/jobs")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
public class JobPostingAdminController {

    private final JobPostingAdminService jobPostingAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<JobPosting>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(jobPostingAdminService.getAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<JobPosting>> create(
            @Valid @RequestBody JobPostingRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(jobPostingAdminService.create(request), "Tạo vị trí tuyển dụng thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<JobPosting>> update(
            @PathVariable UUID id,
            @Valid @RequestBody JobPostingRequest request) {
        return ResponseEntity.ok(ApiResponse.success(jobPostingAdminService.update(id, request), "Cập nhật vị trí tuyển dụng thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        jobPostingAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Đã đóng vị trí tuyển dụng"));
    }
}
