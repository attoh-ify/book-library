package org.example.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("PHYSICAL")
public class PhysicalBook extends Book {
    private int quantityAvailable;

    public PhysicalBook() {}

    public PhysicalBook(
            String title,
            String author,
            String isbn,
            LocalDateTime publicationDate,
            Enums.GENRES genre,
            Enums.BOOK_FORMAT format,
            int price,
            int quantityAvailable
    ) {
        super(title, author, isbn, publicationDate, genre, format, price);
        this.quantityAvailable = quantityAvailable;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }
}