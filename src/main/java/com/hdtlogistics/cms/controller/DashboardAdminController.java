package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.DashboardStatsResponse;
import com.hdtlogistics.cms.service.DashboardAdminService;
import com.hdtlogistics.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/dashboard")
@RequiredArgsConstructor
public class DashboardAdminController {

    private final DashboardAdminService dashboardAdminService;

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ApiResponse<DashboardStatsResponse>> getStats() {
        return ResponseEntity.ok(ApiResponse.success(dashboardAdminService.getStats()));
    }
}
