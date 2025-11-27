package org.example.dtos.auth;

import org.example.dtos.user.UserResponse;
import org.example.models.User;

import java.time.LocalDateTime;

public class LoginResponse {
    private String status;
    private String token;
    private LocalDateTime expiry;
    private String twoFactorAuth;
    private UserResponse user;

    public LoginResponse(String status, String token, LocalDateTime expiry, String twoFactorAuth, User user) {
        this.status = status;
        this.token = token;
        this.expiry = expiry;
        this.twoFactorAuth = twoFactorAuth;
        this.user = new UserResponse(user);
    }

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }

    public String getTwoFactorAuth() {
        return twoFactorAuth;
    }

    public UserResponse getUser() {
        return user;
    }
}