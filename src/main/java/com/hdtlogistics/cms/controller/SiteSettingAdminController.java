package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.entity.SiteSetting;
import com.hdtlogistics.cms.service.SiteSettingAdminService;
import com.hdtlogistics.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/settings")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class SiteSettingAdminController {

    private final SiteSettingAdminService siteSettingAdminService;

    /** Lấy toàn bộ settings */
    @GetMapping
    public ResponseEntity<ApiResponse<List<SiteSetting>>> getAll() {
        return ResponseEntity.ok(ApiResponse.success(siteSettingAdminService.getAll()));
    }

    /**
     * Cập nhật nhiều key cùng lúc.
     * Body: { "hotline": "0929 959 699", "email": "info@hdtlogistics.vn", ... }
     */
    @PatchMapping
    public ResponseEntity<ApiResponse<List<SiteSetting>>> upsert(
            @RequestBody Map<String, String> updates) {
        return ResponseEntity.ok(ApiResponse.success(
                siteSettingAdminService.upsert(updates),
                "Cập nhật cài đặt thành công"));
    }
}
