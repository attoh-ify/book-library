package org.example.dtos.auth;

public class LogoutResponse {
    private String status;
    private String message;

    public LogoutResponse(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}