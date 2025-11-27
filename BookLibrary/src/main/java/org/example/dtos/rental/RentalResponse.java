package org.example.dtos.rental;

import org.example.dtos.book.BookResponse;
import org.example.dtos.user.UserResponse;
import org.example.models.*;

import java.time.LocalDateTime;

public class RentalResponse {
    private String rentalId;
    private UserResponse user;
    private BookResponse book;
    private LocalDateTime rentalDate;
    private LocalDateTime dueDate;
    private LocalDateTime returnedDate;
    private Enums.RENTAL_STATUS status;

    public RentalResponse(Rental rental, User user, Book book) {
        this.rentalId = rental.getId();
        this.user = new UserResponse(user);
        this.book = new BookResponse(book);
        this.rentalDate = rental.getRentalDate();
        this.dueDate = rental.getDueDate();
        this.returnedDate = rental.getReturnedDate();
        this.status = rental.getStatus();
    }

    public String getRentalId() {
        return rentalId;
    }

    public UserResponse getUser() {
        return user;
    }

    public BookResponse getBook() {
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
}