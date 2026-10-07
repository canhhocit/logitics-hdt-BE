package com.hdlogistics.cms.controller;

import com.hdlogistics.cms.entity.Category;
import com.hdlogistics.cms.service.CategoryService;
import com.hdlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // ===== ADMIN ENDPOINTS =====

    @GetMapping("/api/v1/admin/categories")
    public ResponseEntity<ApiResponse<List<Category>>> adminList() {
        return ResponseEntity.ok(ApiResponse.success(categoryService.findAll()));
    }

    @PostMapping("/api/v1/admin/categories")
    public ResponseEntity<ApiResponse<Category>> create(@RequestBody Category category) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.create(category), "Tạo chuyên mục thành công"));
    }

    @PutMapping("/api/v1/admin/categories/{id}")
    public ResponseEntity<ApiResponse<Category>> update(@PathVariable UUID id, @RequestBody Category category) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.update(id, category), "Cập nhật chuyên mục thành công"));
    }

    @DeleteMapping("/api/v1/admin/categories/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa chuyên mục thành công"));
    }

    // ===== PUBLIC ENDPOINTS =====
    // GET /api/v1/public/categories đã được xử lý bởi CmsPublicController

    @GetMapping("/api/v1/public/categories/{slug}")
    public ResponseEntity<ApiResponse<Category>> publicGetBySlug(@PathVariable String slug) {
        return ResponseEntity.ok(ApiResponse.success(categoryService.findBySlug(slug)));
    }
}
