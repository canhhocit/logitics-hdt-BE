package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.MediaResponse;
import com.hdtlogistics.cms.service.MediaService;
import com.hdtlogistics.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/media")
@RequiredArgsConstructor
public class MediaAdminController {

    private final MediaService mediaService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<MediaResponse>> uploadFile(@RequestParam("file") MultipartFile file) {
        MediaResponse response = mediaService.uploadFile(file);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<ApiResponse<Void>> deleteFile(@PathVariable UUID id) {
        mediaService.deleteFile(id);
        return ResponseEntity.ok(ApiResponse.success(null));
    }
}
