package com.hdtlogistics.auth.controller;

import com.hdtlogistics.auth.dto.request.LoginRequest;
import com.hdtlogistics.auth.dto.request.RefreshTokenRequest;
import com.hdtlogistics.auth.dto.response.LoginResponse;
import com.hdtlogistics.auth.service.AuthService;
import com.hdtlogistics.common.dto.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(ApiResponse.success(authService.login(request), "Đăng nhập thành công"));
    }

    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<LoginResponse>> refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(ApiResponse.success(authService.refreshToken(request), "Refresh token thành công"));
    }
}
