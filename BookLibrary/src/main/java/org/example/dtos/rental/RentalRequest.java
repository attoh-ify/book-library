package org.example.dtos.rental;

import org.example.models.Enums;

import java.time.LocalDateTime;

public class RentalRequest {
    private String email;
    private String isbn;
    private LocalDateTime dueDate;
    private Enums.RENTAL_STATUS status;

    public RentalRequest() {}

    public String getEmail() {
        return email;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public Enums.RENTAL_STATUS getStatus() {
        return status;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }

    public void setStatus(Enums.RENTAL_STATUS status) {
        this.status = status;
    }
}