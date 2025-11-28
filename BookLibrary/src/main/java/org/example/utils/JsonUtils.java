package org.example.utils;

import org.example.dtos.purchase.PurchaseResponse;
import org.example.dtos.rental.RentalResponse;

import javax.json.Json;
import javax.json.JsonObject;
import java.time.LocalDateTime;

public class JsonUtils {
    public static JsonObject RentalResponseToJson(RentalResponse rental) {
        return Json.createObjectBuilder()
                .add("rentalId", rental.getRentalId())
                .add("isbn", rental.getBook().getIsbn())
                .add("rentalDate", rental.getRentalDate().toString())
                .add("dueDate", rental.getDueDate().toString())
                .add("returnedDate", rental.getReturnedDate() != null ? rental.getReturnedDate().toString() : "")
                .add("status", rental.getStatus().toString())
                .build();
    }

    public static JsonObject PurchaseResponseToJson(PurchaseResponse purchase) {
        LocalDateTime purchaseDate = purchase.getPurchaseDate();
        return Json.createObjectBuilder()
                .add("purchaseId", purchase.getPurchaseId())
                .add("isbn", purchase.getBook().getIsbn())
                .add("purchaseDate", purchaseDate != null ? purchaseDate.toString() : "")
                .add("pricePaid", purchase.getPricePaid())
                .build();
    }
}
