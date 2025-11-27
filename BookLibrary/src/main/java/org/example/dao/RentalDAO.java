package org.example.dao;

import org.example.dtos.rental.RentalResponse;
import org.example.exceptions.BadRequestException;
import org.example.models.Enums;
import org.example.models.Rental;
import org.example.models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class RentalDAO {
    @PersistenceContext(unitName = "BOOKLIBUnit")
    EntityManager em;

    public void create(Rental rental) {
        em.persist(rental);
    }

    public Rental findById(String id) {
        return em.find(Rental.class, id);
    }

    public void update(Rental rental) {
        em.merge(rental);
    }

    public Rental checkUserRental(String userId, String bookId) {
        TypedQuery<Rental> query = em.createQuery(
                "SELECT r FROM Rental r WHERE r.user_id = :user_id AND r.book_id = :book_id", Rental.class
        );
        query.setParameter("user_id", userId);
        query.setParameter("book_id", bookId);
        List<Rental> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public List<Rental> allActiveRentals() {
        TypedQuery<Rental> query = em.createQuery(
                "SELECT r FROM Rental r WHERE r.status = :status", Rental.class
        );
        query.setParameter("status", Enums.RENTAL_STATUS.valueOf("RENTED"));
        return query.getResultList();
    }

    public List<Rental> allOverdueRentals() {
        TypedQuery<Rental> query = em.createQuery(
                "SELECT r FROM Rental r WHERE r.status = :status AND r.due_date > :now", Rental.class
        );
        query.setParameter("status", Enums.RENTAL_STATUS.valueOf("RENTED"));
        query.setParameter("due_date", LocalDateTime.now());
        return query.getResultList();
    }

    public List<RentalResponse> rentalHistory(String email) {
        TypedQuery<Rental> rentalQuery = em.createQuery(
                "SELECT r FROM Rental r WHERE r.user.email = :email", Rental.class
        );
        rentalQuery.setParameter("email", email);
        List<Rental> rentals = rentalQuery.getResultList();
        List<RentalResponse> rentalResponseList = new ArrayList<RentalResponse>();

        TypedQuery<User> userQuery = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class
        );
        userQuery.setParameter("email", email);
        List<User> result = userQuery.getResultList();
        User user = result.get(0);
        if (user == null) {
            throw new BadRequestException("User not found for the email provided");
        }

        if (!rentals.isEmpty()) {
            for (Rental rental : rentals) {
                rentalResponseList.add(new RentalResponse(rental, user, rental.getBook()));
            }
        } else {
            return null;
        }
        return rentalResponseList;
    }
}