package org.example.models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@DiscriminatorValue("EBOOK")
public class EBook extends Book {
    private String downloadLink;

    public EBook() {}

    public EBook(
            String title,
            String author,
            String isbn,
            LocalDateTime publicationDate,
            Enums.GENRES genre,
            Enums.BOOK_FORMAT format,
            int price,
            String downloadLink
    ) {
        super(title, author, isbn, publicationDate, genre, format, price);
        this.downloadLink = downloadLink;
    }

    public String getDownloadLink() {
        return downloadLink;
    }
}