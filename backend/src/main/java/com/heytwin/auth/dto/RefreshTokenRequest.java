package com.heytwin.auth.dto;

import jakarta.validation.constraints.NotBlank;

public class RefreshTokenRequest {
    @NotBlank
    private String refreshToken;

    @java.lang.SuppressWarnings("all")
    
    public RefreshTokenRequest() {
    }

    @java.lang.SuppressWarnings("all")
    
    public String getRefreshToken() {
        return this.refreshToken;
    }

    @java.lang.SuppressWarnings("all")
    
    public void setRefreshToken(final String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public boolean equals(final java.lang.Object o) {
        if (o == this) return true;
        if (!(o instanceof RefreshTokenRequest)) return false;
        final RefreshTokenRequest other = (RefreshTokenRequest) o;
        if (!other.canEqual((java.lang.Object) this)) return false;
        final java.lang.Object this$refreshToken = this.getRefreshToken();
        final java.lang.Object other$refreshToken = other.getRefreshToken();
        if (this$refreshToken == null ? other$refreshToken != null : !this$refreshToken.equals(other$refreshToken)) return false;
        return true;
    }

    @java.lang.SuppressWarnings("all")
    
    protected boolean canEqual(final java.lang.Object other) {
        return other instanceof RefreshTokenRequest;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final java.lang.Object $refreshToken = this.getRefreshToken();
        result = result * PRIME + ($refreshToken == null ? 43 : $refreshToken.hashCode());
        return result;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("all")
    
    public java.lang.String toString() {
        return "RefreshTokenRequest(refreshToken=" + this.getRefreshToken() + ")";
    }
}
