package org.example.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "rentals")
public class Rental {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private PhysicalBook book;

    @Column(nullable = false)
    private LocalDateTime rentalDate;

    @Column(nullable = false)
    private LocalDateTime dueDate;

    @Column(nullable = true)
    private LocalDateTime returnedDate;

    @Column(nullable = false)
    private Enums.RENTAL_STATUS status;

    public Rental() {}

    public Rental(
            User user,
            PhysicalBook book,
            LocalDateTime rentalDate,
            LocalDateTime dueDate,
            Enums.RENTAL_STATUS status
    ) {
        this.user = user;
        this.book = book;
        this.rentalDate = rentalDate;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public PhysicalBook getBook() {
        return book;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public LocalDateTime getReturnedDate() {
        return returnedDate;
    }

    public Enums.RENTAL_STATUS getStatus() {
        return status;
    }

    public void setStatus(Enums.RENTAL_STATUS status) {
        this.status = status;
    }

    public void setReturnedDate(LocalDateTime returnedDate) {
        this.returnedDate = returnedDate;
    }
}