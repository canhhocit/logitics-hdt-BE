package com.hdlogistics.lead.controller;

import com.hdlogistics.lead.dto.LeadStatusUpdateRequest;
import com.hdlogistics.lead.entity.Lead;
import com.hdlogistics.lead.service.LeadAdminService;
import com.hdlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/leads")
@RequiredArgsConstructor
public class LeadAdminController {

    private final LeadAdminService leadAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Lead>>> getAllLeads(
            Pageable pageable,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(ApiResponse.success(leadAdminService.getAllLeads(pageable, status)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Lead>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(ApiResponse.success(leadAdminService.getById(id)));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<Lead>> updateStatus(
            @PathVariable UUID id,
            @Valid @RequestBody LeadStatusUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success(leadAdminService.updateStatus(id, request), "Cập nhật trạng thái thành công"));
    }
}
