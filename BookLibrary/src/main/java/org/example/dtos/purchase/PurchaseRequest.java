package org.example.dtos.purchase;

public class PurchaseRequest {
    private String isbn;
    private String email;

    public PurchaseRequest() {}

    public String getIsbn() {
        return isbn;
    }

    public String getEmail() {
        return email;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}