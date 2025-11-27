package org.example.dtos.rental;

public class ReturnBookResponse {
    private String rentalId;
    private String message;

    public ReturnBookResponse(String rentalId, String message) {
        this.rentalId = rentalId;
        this.message = message;
    }

    public String getRentalId() {
        return rentalId;
    }

    public String getMessage() {
        return message;
    }
}