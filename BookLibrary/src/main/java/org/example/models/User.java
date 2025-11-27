package org.example.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String passwordHash;

    @Enumerated(EnumType.STRING)
    private Enums.ROLES role;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    public User() {}

    public User(String name, String email, String passwordHash, Enums.ROLES role) {
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.registrationDate = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public Enums.ROLES getRole() {
        return role;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
}