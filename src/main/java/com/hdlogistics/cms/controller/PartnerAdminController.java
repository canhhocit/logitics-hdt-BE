package com.hdlogistics.cms.controller;

import com.hdlogistics.cms.dto.request.PartnerRequest;
import com.hdlogistics.cms.entity.Partner;
import com.hdlogistics.cms.service.PartnerAdminService;
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
@RequestMapping("/api/v1/admin/partners")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN') or hasRole('EDITOR')")
public class PartnerAdminController {

    private final PartnerAdminService partnerAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Partner>>> getAll(
            @RequestParam(required = false) String type) {
        return ResponseEntity.ok(ApiResponse.success(partnerAdminService.getAll()));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Partner>> create(@Valid @RequestBody PartnerRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(partnerAdminService.create(request), "Tạo đối tác thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Partner>> update(
            @PathVariable UUID id, @Valid @RequestBody PartnerRequest request) {
        return ResponseEntity.ok(ApiResponse.success(partnerAdminService.update(id, request), "Cập nhật đối tác thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        partnerAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa đối tác thành công"));
    }
}
