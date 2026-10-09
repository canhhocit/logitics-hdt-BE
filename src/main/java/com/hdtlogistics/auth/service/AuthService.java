package com.hdtlogistics.auth.service;

import com.hdtlogistics.auth.dto.request.LoginRequest;
import com.hdtlogistics.auth.dto.request.RefreshTokenRequest;
import com.hdtlogistics.auth.dto.response.LoginResponse;
import com.hdtlogistics.auth.entity.AuthToken;
import com.hdtlogistics.auth.entity.User;
import com.hdtlogistics.auth.repository.AuthTokenRepository;
import com.hdtlogistics.auth.repository.UserRepository;
import com.hdtlogistics.auth.security.JwtService;
import com.hdtlogistics.auth.security.UserDetailsImpl;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final AuthTokenRepository tokenRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    @Transactional
    public LoginResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (AuthenticationException e) {
            throw new BaseException("Sai tên đăng nhập hoặc mật khẩu", ErrorCode.UNAUTHORIZED, 401);
        }

        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new BaseException("Người dùng không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        if (!user.getIsActive()) {
            throw new BaseException("Tài khoản đã bị khóa", ErrorCode.FORBIDDEN, 403);
        }

        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        String accessToken = jwtService.generateToken(userDetails);
        String refreshToken = UUID.randomUUID().toString();

        // Save refresh token
        AuthToken tokenEntity = AuthToken.builder()
                .user(user)
                .token(refreshToken)
                .expiryDate(LocalDateTime.now().plusSeconds(refreshExpiration / 1000))
                .build();
        tokenRepository.save(tokenEntity);

        List<String> roles = user.getRoleCodes() != null ? 
                Arrays.stream(user.getRoleCodes().split(",")).collect(Collectors.toList()) : 
                List.of();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .expiresIn(jwtExpiration / 1000)
                .tokenType("Bearer")
                .user(LoginResponse.UserDto.builder()
                        .id(user.getId().toString())
                        .username(user.getUsername())
                        .fullName(user.getFullName())
                        .roles(roles)
                        .build())
                .build();
    }

    @Transactional
    public LoginResponse refreshToken(RefreshTokenRequest request) {
        AuthToken authToken = tokenRepository.findByToken(request.getRefreshToken())
                .orElseThrow(() -> new BaseException("Refresh token không hợp lệ", ErrorCode.UNAUTHORIZED, 401));

        if (authToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(authToken);
            throw new BaseException("Refresh token đã hết hạn", ErrorCode.UNAUTHORIZED, 401);
        }

        User user = authToken.getUser();
        UserDetailsImpl userDetails = new UserDetailsImpl(user);
        String accessToken = jwtService.generateToken(userDetails);

        List<String> roles = user.getRoleCodes() != null ? 
                Arrays.stream(user.getRoleCodes().split(",")).collect(Collectors.toList()) : 
                List.of();

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(request.getRefreshToken())
                .expiresIn(jwtExpiration / 1000)
                .tokenType("Bearer")
                .user(LoginResponse.UserDto.builder()
                        .id(user.getId().toString())
                        .username(user.getUsername())
                        .fullName(user.getFullName())
                        .roles(roles)
                        .build())
                .build();
    }
}
