package org.example.services;

import org.example.dao.BookDAO;
import org.example.dao.RentalDAO;
import org.example.dao.UserDAO;
import org.example.dtos.rental.RentalRequest;
import org.example.dtos.rental.RentalResponse;
import org.example.dtos.rental.ReturnBookRequest;
import org.example.dtos.rental.ReturnBookResponse;
import org.example.exceptions.AppException;
import org.example.exceptions.BadRequestException;
import org.example.models.*;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class RentalService {
    @Inject
    private RentalDAO rentalDAO;

    @Inject
    private UserDAO userDAO;

    @Inject
    private BookDAO bookDAO;

    public RentalResponse rentBook(RentalRequest req) {
        // Validate user exists
        String email = req.getEmail();
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new BadRequestException("User with the email provided does not exist");
        }

        // Validate book exists
        String isbn = req.getIsbn();
        Book book = bookDAO.findByIsbn(isbn);
        if (book == null) {
            throw new BadRequestException("Book with the isbn provided does not exist");
        }

        // Check that book is PHYSICAL
        if (!(book instanceof PhysicalBook)) {
            throw new BadRequestException("You can only rent a physical book");
        }

        // Check quantityAvailable > 0
        if (bookDAO.bookCount(isbn) < 0) {
            throw new AppException("This book is currently not in stock", 400);
        }

        // Ensure user does not already have an active rental for that book
        List<RentalResponse> usersRentals = rentalDAO.rentalHistory(email);
        if (usersRentals != null) {
            for (RentalResponse rental : usersRentals) {
                if (Objects.equals(rental.getBook().getIsbn(), isbn)) {
                    throw new AppException("You have already rented this book", 400);
                }
            }
        }

        // Create rental record
        Rental rental = new Rental(
                user,
                (PhysicalBook) book,
                LocalDateTime.now(),
                req.getDueDate(),
                req.getStatus()
                );
        rentalDAO.create(rental);

        // Decrease quantityAvailable
        bookDAO.decrementBookCount(isbn);

        return new RentalResponse(rental, user, book);
    }

    public ReturnBookResponse returnBook(ReturnBookRequest req) {
        String rentalId = req.getRentalId();
        Rental rental = rentalDAO.findById(rentalId);
        if (rental == null) {
            throw new BadRequestException("Rental with the rentalId passed does not exist");
        }

        if (rental.getStatus() == Enums.RENTAL_STATUS.RETURNED) {
            throw new BadRequestException("Rental with the rentalId passed has already been returned");
        }

        rental.setStatus(Enums.RENTAL_STATUS.RETURNED);
        rental.setReturnedDate(LocalDateTime.now());
        rentalDAO.update(rental);

        bookDAO.incrementBookCount(req.getIsbn());

        return new ReturnBookResponse(rentalId, "Book successfully returned");
    }
}