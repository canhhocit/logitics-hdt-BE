package com.hdlogistics.auth.dto.response;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class LoginResponse {
    private String accessToken;
    private String refreshToken;
    private long expiresIn;
    private String tokenType;
    private UserDto user;

    @Data
    @Builder
    public static class UserDto {
        private String id;
        private String username;
        private String fullName;
        private List<String> roles;
    }
}
