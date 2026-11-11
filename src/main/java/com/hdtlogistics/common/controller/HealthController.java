package com.hdtlogistics.common.controller;

import com.hdtlogistics.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@Tag(name = "Health & Keep-Alive", description = "Public health check and keep-alive ping endpoints")
public class HealthController {

    @RequestMapping(
            value = {"/api/v1/public/ping", "/ping", "/health"},
            method = {RequestMethod.GET, RequestMethod.HEAD}
    )
    @Operation(summary = "Keep-alive ping", description = "Public endpoint to keep the Render service active and check health status")
    public ResponseEntity<ApiResponse<Map<String, Object>>> ping() {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("status", "UP");
        data.put("service", "hdtlogistics-backend");
        data.put("timestamp", Instant.now().toString());
        return ResponseEntity.ok(ApiResponse.success(data, "pong"));
    }
}
