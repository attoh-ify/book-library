package org.example.interfaces;

import org.example.models.Book;
import org.example.models.Enums;

import java.util.List;

public interface BookDAOInt {
    void add(Book book);
    Book update(Book book);
    Book findById(String id);
    List<Book> findAll();
    List<Book> findByAuthor(String author);
    List<Book> findByGenre(Enums.GENRES genre);
    List<Book> findByFormat(Enums.BOOK_FORMAT format);
    List<Book> findByTitle(String title);
    Book findByIsbn(String isbn);
    void remove(String isbn);
    int bookCount(String id);
    boolean isbnIsUnique(String isbn);
    void incrementBookCount(String isbn);
    void decrementBookCount(String isbn);
}