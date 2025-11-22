package com.heytwin.auth.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthResponse {
    String accessToken;
    String refreshToken;
    long expiresIn;
    UserSummary user;

    @Value
    @Builder
    public static class UserSummary {
        String id;
        String fullName;
        String role;
    }
}
