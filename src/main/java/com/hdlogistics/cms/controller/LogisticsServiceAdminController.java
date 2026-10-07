package com.hdlogistics.cms.controller;

import com.hdlogistics.cms.dto.request.ServiceRequest;
import com.hdlogistics.cms.entity.LogisticsService;
import com.hdlogistics.cms.service.LogisticsServiceAdminService;
import com.hdlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/services")
@RequiredArgsConstructor
public class LogisticsServiceAdminController {

    private final LogisticsServiceAdminService serviceAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<LogisticsService>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(serviceAdminService.getAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<LogisticsService>> create(@Valid @RequestBody ServiceRequest request) {
        return ResponseEntity.ok(ApiResponse.success(serviceAdminService.create(request), "Tạo dịch vụ thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<LogisticsService>> update(
            @PathVariable UUID id,
            @Valid @RequestBody ServiceRequest request) {
        return ResponseEntity.ok(ApiResponse.success(serviceAdminService.update(id, request), "Cập nhật dịch vụ thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        serviceAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa dịch vụ thành công"));
    }
}
