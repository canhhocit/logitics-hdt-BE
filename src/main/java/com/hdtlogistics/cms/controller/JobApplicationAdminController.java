package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.JobApplicationResponse;
import com.hdtlogistics.cms.dto.request.JobApplicationStatusRequest;
import com.hdtlogistics.cms.service.JobApplicationAdminService;
import com.hdtlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/applications")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
public class JobApplicationAdminController {

    private final JobApplicationAdminService applicationAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<JobApplicationResponse>>> getAll(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) UUID jobId,
            @RequestParam(required = false) String keyword,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<JobApplicationResponse> page = applicationAdminService.getAll(pageable, status, jobId, keyword);
        return ResponseEntity.ok(ApiResponse.success(page));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<JobApplicationResponse>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(applicationAdminService.getById(id)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<JobApplicationResponse>> updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody JobApplicationStatusRequest request) {

        JobApplicationResponse updated = applicationAdminService.updateStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success(updated, "Cập nhật trạng thái hồ sơ ứng tuyển thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        applicationAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Đã xóa hồ sơ ứng tuyển"));
    }
}
