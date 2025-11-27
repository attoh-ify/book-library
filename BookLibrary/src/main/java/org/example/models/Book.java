package org.example.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "books")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "format", discriminatorType = DiscriminatorType.STRING)
public abstract class Book {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column(nullable = false)
    private LocalDateTime publicationDate;

    @Column(nullable = false)
    private Enums.GENRES genre;

    @Column(name = "book_format", nullable = false)
    @Enumerated(EnumType.STRING)
    private Enums.BOOK_FORMAT bookFormat;

    @Column(nullable = false)
    private int price;

    public Book() {}

    public Book(
            String title,
            String author,
            String isbn,
            LocalDateTime publicationDate,
            Enums.GENRES genre,
            Enums.BOOK_FORMAT format,
            int price
    ) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.genre = genre;
        this.bookFormat = format;
        this.price = price;
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
        return bookFormat;
    }

    public int getPrice() {
        return price;
    }
}