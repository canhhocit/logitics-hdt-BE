package com.hdlogistics.lead.controller;

import com.hdlogistics.common.dto.ApiResponse;
import com.hdlogistics.lead.dto.ContactPersonRequest;
import com.hdlogistics.lead.entity.ContactPerson;
import com.hdlogistics.lead.service.ContactPersonAdminService;
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
@RequestMapping("/api/v1/admin/contacts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class ContactPersonAdminController {

    private final ContactPersonAdminService contactPersonAdminService;

    @GetMapping
    public ResponseEntity<ApiResponse<Page<ContactPerson>>> getAll(Pageable pageable) {
        return ResponseEntity.ok(ApiResponse.success(contactPersonAdminService.getAll(pageable)));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<ContactPerson>> create(
            @Valid @RequestBody ContactPersonRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success(contactPersonAdminService.create(request), "Tạo người phụ trách thành công"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ContactPerson>> update(
            @PathVariable UUID id,
            @Valid @RequestBody ContactPersonRequest request) {
        return ResponseEntity.ok(ApiResponse.success(contactPersonAdminService.update(id, request), "Cập nhật người phụ trách thành công"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable UUID id) {
        contactPersonAdminService.delete(id);
        return ResponseEntity.ok(ApiResponse.success(null, "Xóa người phụ trách thành công"));
    }
}
