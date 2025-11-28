package org.example.services;

import org.example.dao.BookDAO;
import org.example.dao.PurchaseDAO;
import org.example.dao.UserDAO;
import org.example.dtos.purchase.PurchaseRequest;
import org.example.dtos.purchase.PurchaseResponse;
import org.example.exceptions.BadRequestException;
import org.example.models.*;
import org.example.utils.JsonUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@RequestScoped
public class PurchaseService {
    @Inject
    private UserDAO userDAO;

    @Inject
    private BookDAO bookDAO;

    @Inject
    private PurchaseDAO purchaseDAO;

    public PurchaseResponse buyEBook(PurchaseRequest req) {
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

        // Check that book is EBOOK
        if (!(book instanceof EBook)) {
            throw new BadRequestException("You can only buy a ebook book");
        }

        // Check user has not bought it before
        List<PurchaseResponse> userPurchases = purchaseDAO.purchaseHistory(email);
        if (userPurchases != null) {
            for (PurchaseResponse purchase : userPurchases) {
                if (Objects.equals(purchase.getBook().getIsbn(), isbn)) {
                    throw new BadRequestException("You have already bought this ebook", JsonUtils.PurchaseResponseToJson(purchase));
                }
            }
        }

        // Create purchase record
        Purchase purchase = new Purchase(
                user,
                (EBook) book,
                LocalDateTime.now(),
                book.getPrice()
        );
        purchaseDAO.create(purchase);

        return new PurchaseResponse(purchase, user, book);
    }
}
