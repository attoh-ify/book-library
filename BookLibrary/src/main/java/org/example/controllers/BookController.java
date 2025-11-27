package org.example.controllers;

import org.example.dao.BookDAO;
import org.example.dtos.book.AddBookRequest;
import org.example.dtos.book.BookResponse;
import org.example.filters.Secured;
import org.example.models.Book;
import org.example.models.Enums;
import org.example.services.BookService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/booklib/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {
    @Inject
    private BookDAO bookDAO;

    @Inject
    private BookService bookService;

    @POST
    @Secured
    public Response addBook(AddBookRequest request) {
        Book book = bookService.addBook(request);
        BookResponse bookResponse = new BookResponse(book);
        return Response.ok(bookResponse).build();
    }

    @POST
    @Secured
    @Path("/batch")
    public Response addBookBatch(List<AddBookRequest> request) {
        List<BookResponse> bookResponses = new ArrayList<BookResponse>();
        for (AddBookRequest bookReq: request) {
            Book book = bookService.addBook(bookReq);
            BookResponse bookResponse = new BookResponse(book);
            bookResponses.add(bookResponse);
        }
        return Response.ok(bookResponses).build();
    }

    @DELETE
    @Secured
    @Path("/{isbn}")
    public Response removeBook(@PathParam("isbn") String isbn) {
        bookService.removeBook(isbn);
        return Response.ok().build();
    }

    @GET
    @Secured
    public Response findBook(
            @QueryParam("bookId") String bookId,
            @QueryParam("isbn") String isbn,
            @QueryParam("title") String title,
            @QueryParam("author") String author,
            @QueryParam("genre") Enums.GENRES genre,
            @QueryParam("format") Enums.BOOK_FORMAT format
    ) {
        if (bookId != null) {
            Book book = bookDAO.findById(bookId);
            BookResponse bookResponse = new BookResponse(book);
            return Response.ok(bookResponse).build();
        }

        if (isbn != null) {
            Book book = bookDAO.findByIsbn(isbn);
            BookResponse bookResponse = new BookResponse(book);
            return Response.ok(bookResponse).build();
        }

        if (title != null) {
            List<Book> books = bookDAO.findByTitle(title);
            List<BookResponse> bookResponses = new ArrayList<BookResponse>();
            for (Book book: books) {
                BookResponse bookResponse = new BookResponse(book);
                bookResponses.add(bookResponse);
            }
            return Response.ok(bookResponses).build();
        }

        if (author != null) {
            List<Book> books = bookDAO.findByAuthor(author);
            List<BookResponse> bookResponses = new ArrayList<BookResponse>();
            for (Book book: books) {
                BookResponse bookResponse = new BookResponse(book);
                bookResponses.add(bookResponse);
            }
            return Response.ok(bookResponses).build();
        }

        if (genre != null) {
            List<Book> books = bookDAO.findByGenre(genre);
            List<BookResponse> bookResponses = new ArrayList<BookResponse>();
            for (Book book: books) {
                BookResponse bookResponse = new BookResponse(book);
                bookResponses.add(bookResponse);
            }
            return Response.ok(bookResponses).build();
        }

        if (format != null) {
            List<Book> books = bookDAO.findByFormat(format);
            List<BookResponse> bookResponses = new ArrayList<BookResponse>();
            for (Book book: books) {
                BookResponse bookResponse = new BookResponse(book);
                bookResponses.add(bookResponse);
            }
            return Response.ok(bookResponses).build();
        }

        List<Book> books = bookDAO.findAll();
        List<BookResponse> bookResponses = new ArrayList<BookResponse>();
        for (Book book: books) {
            BookResponse bookResponse = new BookResponse(book);
            bookResponses.add(bookResponse);
        }
        return Response.ok(bookResponses).build();
    }
}