package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.request.CompanyValueRequest;
import com.hdtlogistics.cms.entity.CompanyValue;
import com.hdtlogistics.cms.service.CompanyValueAdminService;
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
@RequestMapping("/api/v1/admin/company-values")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
public class CompanyValueAdminController {

    private final CompanyValueAdminService companyValueAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<CompanyValue>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(companyValueAdminService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<CompanyValue>> create(
            @Valid @RequestBody CompanyValueRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(companyValueAdminService.create(request), "Tạo giá trị cốt lõi thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CompanyValue>> update(
            @PathVariable UUID id,
            @Valid @RequestBody CompanyValueRequest request) {
        return ResponseEntity.ok(ApiResponse.success(companyValueAdminService.update(id, request), "Cập nhật giá trị cốt lõi thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        companyValueAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa giá trị cốt lõi thành công"));
    }
}
