package com.hdlogistics.cms.controller;

import com.hdlogistics.cms.dto.request.PostRequest;
import com.hdlogistics.cms.entity.Post;
import com.hdlogistics.cms.service.PostAdminService;
import com.hdlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/admin/posts")
@RequiredArgsConstructor
public class PostAdminController {

    private final PostAdminService postAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<Post>>> getAll(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime fromDate,
            @RequestParam(required = false) @org.springframework.format.annotation.DateTimeFormat(iso = org.springframework.format.annotation.DateTimeFormat.ISO.DATE_TIME) java.time.LocalDateTime toDate,
            Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(postAdminService.getAll(keyword, fromDate, toDate, pageable)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Post>> create(@Valid @RequestBody PostRequest request) {
        return ResponseEntity.ok(ApiResponse.success(postAdminService.create(request), "Tạo bài viết thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> update(
            @PathVariable UUID id,
            @Valid @RequestBody PostRequest request) {
        return ResponseEntity.ok(ApiResponse.success(postAdminService.update(id, request), "Cập nhật bài viết thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        postAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa bài viết thành công"));
    }
}
