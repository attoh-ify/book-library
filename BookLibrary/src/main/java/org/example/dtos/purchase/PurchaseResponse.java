package org.example.dtos.purchase;

import org.example.dtos.user.UserResponse;
import org.example.dtos.book.BookResponse;
import org.example.models.*;

import java.time.LocalDateTime;

public class PurchaseResponse {
    private String purchaseId;
    private UserResponse user;
    private BookResponse book;
    private LocalDateTime purchaseDate;
    private int pricePaid;

    public PurchaseResponse(Purchase purchase, User user, Book book) {
        this.purchaseId = purchase.getId();
        this.user = new UserResponse(user);
        this.book = new BookResponse(book);
        this.purchaseDate = purchase.getPurchaseDate();
        this.pricePaid = purchase.getPricePaid();
    }

    public String getPurchaseId() {
        return purchaseId;
    }

    public UserResponse getUser() {
        return user;
    }

    public BookResponse getBook() {
        return book;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public int getPricePaid() {
        return pricePaid;
    }
}