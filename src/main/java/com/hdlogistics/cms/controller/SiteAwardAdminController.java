package com.hdlogistics.cms.controller;

import com.hdlogistics.cms.dto.request.SiteAwardRequest;
import com.hdlogistics.cms.entity.SiteAward;
import com.hdlogistics.cms.service.SiteAwardAdminService;
import com.hdlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/awards")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
public class SiteAwardAdminController {

    private final SiteAwardAdminService siteAwardAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<SiteAward>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(siteAwardAdminService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<SiteAward>> create(
            @Valid @RequestBody SiteAwardRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(siteAwardAdminService.create(request), "Tạo giải thưởng thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<SiteAward>> update(
            @PathVariable UUID id,
            @Valid @RequestBody SiteAwardRequest request) {
        return ResponseEntity.ok(ApiResponse.success(siteAwardAdminService.update(id, request), "Cập nhật giải thưởng thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        siteAwardAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa giải thưởng thành công"));
    }
}
