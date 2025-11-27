package org.example.dtos.user;

import org.example.models.Enums;

public class CreateUserRequest {
    private String name;
    private String email;
    private String password;
    private Enums.ROLES role;

    public CreateUserRequest() {}

    public String getName() { return name; }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Enums.ROLES getRole() {
        return role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Enums.ROLES role) {
        this.role = role;
    }
}