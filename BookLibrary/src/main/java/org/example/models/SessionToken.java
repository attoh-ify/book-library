package org.example.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "session_tokens")
public class SessionToken {
    @Id
    @Column(length = 36)
    private String token = UUID.randomUUID().toString();

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime expiry;

    @Column(nullable = false)
    private LocalDateTime createdTime = LocalDateTime.now();

    public SessionToken() {}

    public SessionToken(User user, LocalDateTime expiry) {
        this.user = user;
        this.expiry = expiry;
        this.createdTime = LocalDateTime.now();
    }

    public LocalDateTime getExpiry() {
        return this.expiry;
    }

    public String getUserId() {
        return user.getId();
    }

    public String getToken() {
        return token;
    }
}