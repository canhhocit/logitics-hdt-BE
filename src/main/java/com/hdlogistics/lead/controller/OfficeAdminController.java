package com.hdlogistics.lead.controller;

import com.hdlogistics.common.dto.ApiResponse;
import com.hdlogistics.lead.dto.OfficeRequest;
import com.hdlogistics.lead.entity.Office;
import com.hdlogistics.lead.service.OfficeAdminService;
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
@RequestMapping("/api/v1/admin/offices")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class OfficeAdminController {

    private final OfficeAdminService officeAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Office>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(officeAdminService.getAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Office>> create(
            @Valid @RequestBody OfficeRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(officeAdminService.create(request), "Tạo chi nhánh thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Office>> update(
            @PathVariable UUID id,
            @Valid @RequestBody OfficeRequest request) {
        return ResponseEntity.ok(ApiResponse.success(officeAdminService.update(id, request), "Cập nhật chi nhánh thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        officeAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa chi nhánh thành công"));
    }
}
