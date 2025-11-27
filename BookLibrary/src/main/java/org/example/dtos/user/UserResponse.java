package org.example.dtos.user;

import org.example.models.Enums;
import org.example.models.User;

public class UserResponse {
    private String userId;
    private String name;
    private String email;
    private Enums.ROLES role;

    public UserResponse(User user) {
        this.userId = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.role = user.getRole();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Enums.ROLES getRole() {
        return role;
    }
}