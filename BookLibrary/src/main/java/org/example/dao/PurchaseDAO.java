package org.example.dao;

import org.example.dtos.purchase.PurchaseResponse;
import org.example.exceptions.BadRequestException;
import org.example.models.Purchase;
import org.example.models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class PurchaseDAO {
    @PersistenceContext(unitName = "BOOKLIBUnit")
    EntityManager em;

    public void create(Purchase purchase) {
        em.persist(purchase);
    }

    public Purchase findById(String id) {
        return em.find(Purchase.class, id);
    }

    public boolean userHasBook(String userId, String bookId) {
        TypedQuery<Purchase> query = em.createQuery(
                "SELECT p FROM Purchase p WHERE p.user_id = :user_id AND p.book_id = :book_id", Purchase.class
        );
        query.setParameter("user_id", userId);
        query.setParameter("book_id", bookId);
        List<Purchase> result = query.getResultList();
        return !result.isEmpty();
    }

    public List<Purchase> userPurchaseHistory(String userId) {
        TypedQuery<Purchase> query = em.createQuery(
                "SELECT p FROM Purchase p WHERE p.user_id = :user_id", Purchase.class
        );
        query.setParameter("user_id", userId);
        return query.getResultList();
    }

    public List<Purchase> bookPurchaseHistory(String bookId) {
        TypedQuery<Purchase> query = em.createQuery(
                "SELECT p FROM Purchase p WHERE p.book_id = :book_id", Purchase.class
        );
        query.setParameter("book_id", bookId);
        return query.getResultList();
    }

    public List<PurchaseResponse> purchaseHistory(String email) {
        TypedQuery<Purchase> purchaseQuery = em.createQuery(
                "SELECT p FROM Purchase p WHERE p.user.email = :email", Purchase.class
        );
        purchaseQuery.setParameter("email", email);
        List<Purchase> purchases = purchaseQuery.getResultList();
        List<PurchaseResponse> purchaseResponseList = new ArrayList<PurchaseResponse>();

        TypedQuery<User> userQuery = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class
        );
        userQuery.setParameter("email", email);
        List<User> result = userQuery.getResultList();
        User user = result.get(0);
        if (user == null) {
            throw new BadRequestException("User not found for the email provided");
        }

        if (!purchases.isEmpty()) {
            for (Purchase purchase : purchases) {
                purchaseResponseList.add(new PurchaseResponse(purchase, user, purchase.getBook()));
            }
        } else {
            return null;
        }
        return purchaseResponseList;
    }
}