package com.hdtlogistics.lead.controller;

import com.hdtlogistics.lead.dto.LeadRequest;
import com.hdtlogistics.lead.entity.Lead;
import com.hdtlogistics.lead.service.LeadPublicService;
import com.hdtlogistics.common.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/public/leads")
@RequiredArgsConstructor
public class LeadPublicController {

    private final LeadPublicService leadPublicService;

    @PostMapping
    public ResponseEntity<ApiResponse<Lead>> submitLead(
            @Valid @RequestBody LeadRequest request,
            HttpServletRequest httpRequest) {
        
        String ipAddress = httpRequest.getHeader("X-Forwarded-For");
        if (ipAddress == null) {
            ipAddress = httpRequest.getRemoteAddr();
        }
        
        Lead lead = leadPublicService.submitLead(request, ipAddress);
        return ResponseEntity.ok(ApiResponse.success(lead, "Gửi thông tin tư vấn thành công"));
    }
}
