package org.example.dtos.rental;

public class ReturnBookRequest {
    private String rentalId;
    private String isbn;

    public ReturnBookRequest() {}

    public String getRentalId() {
        return rentalId;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setRentalId(String rentalId) {
        this.rentalId = rentalId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}