package org.example.dtos.book;

import org.example.models.Book;
import org.example.models.EBook;
import org.example.models.Enums;
import org.example.models.PhysicalBook;

import java.time.LocalDateTime;

public class AddBookRequest {
    private String title;
    private String author;
    private String isbn;
    private LocalDateTime publicationDate;
    private Enums.GENRES genre;
    private Enums.BOOK_FORMAT format;
    private int price;
    private int quantityAvailable;
    private String downloadLink;

    public AddBookRequest() {}

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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPublicationDate(LocalDateTime publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setGenre(Enums.GENRES genre) {
        this.genre = genre;
    }

    public void setFormat(Enums.BOOK_FORMAT format) {
        this.format = format;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }
}