package org.example.services;

import org.example.dao.BookDAO;
import org.example.dtos.book.AddBookRequest;
import org.example.exceptions.BadRequestException;
import org.example.models.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class BookService {
    @Inject
    private BookDAO bookDAO;

    public Book addBook(AddBookRequest req) {
        String isbn = req.getIsbn();
        boolean isbnExists = bookDAO.isbnIsUnique(isbn);

        if (!isbnExists) {
            throw new BadRequestException("A book with this isbn already exists");
        }

        if (req.getFormat() == Enums.BOOK_FORMAT.PHYSICAL) {
            PhysicalBook book = new PhysicalBook(
                    req.getTitle(),
                    req.getAuthor(),
                    isbn,
                    req.getPublicationDate(),
                    req.getGenre(),
                    req.getFormat(),
                    req.getPrice(),
                    req.getQuantityAvailable()
            );
            bookDAO.add(book);
            return book;
        }

        if (req.getFormat() == Enums.BOOK_FORMAT.EBOOK) {
            String downloadLink = req.getDownloadLink();
            if (downloadLink == null) {
                throw new BadRequestException("Download link is required");
            }
            EBook book = new EBook(
                    req.getTitle(),
                    req.getAuthor(),
                    isbn,
                    req.getPublicationDate(),
                    req.getGenre(),
                    req.getFormat(),
                    req.getPrice(),
                    req.getDownloadLink()
            );
            bookDAO.add(book);
            return book;
        }

        throw new BadRequestException("Unknown Book format");
    }

    public void removeBook(String isbn) {
        boolean isbnExists = bookDAO.isbnIsUnique(isbn);
        if (!isbnExists) {
            throw new BadRequestException("No book exists with this isbn");
        }
        bookDAO.remove(isbn);
    }
}