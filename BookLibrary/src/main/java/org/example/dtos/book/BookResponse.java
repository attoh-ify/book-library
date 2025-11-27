package org.example.dtos.book;

import org.example.models.Book;
import org.example.models.EBook;
import org.example.models.Enums;
import org.example.models.PhysicalBook;

import java.time.LocalDateTime;

public class BookResponse {
    private String id;
    private String title;
    private String author;
    private String isbn;
    private LocalDateTime publicationDate;
    private Enums.GENRES genre;
    private Enums.BOOK_FORMAT format;
    private int price;
    private int quantityAvailable;
    private String downloadLink;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.isbn = book.getIsbn();
        this.publicationDate = book.getPublicationDate();
        this.genre = book.getGenre();
        this.format = book.getFormat();
        this.price = book.getPrice();
        if (book instanceof PhysicalBook) {
            this.quantityAvailable = ((PhysicalBook) book).getQuantityAvailable();
        }
        if (book instanceof EBook) {
            this.downloadLink = ((EBook) book).getDownloadLink();
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public LocalDateTime getPublicationDate() {
        return publicationDate;
    }

    public Enums.GENRES getGenre() {
        return genre;
    }

    public Enums.BOOK_FORMAT getFormat() {
        return format;
    }

    public int getPrice() {
        return price;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public String getDownloadLink() {
        return downloadLink;
    }
}