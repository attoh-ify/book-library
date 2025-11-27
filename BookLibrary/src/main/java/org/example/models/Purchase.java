package org.example.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "purchases")
public class Purchase {
    @Id
    @Column(length = 36)
    private String id = UUID.randomUUID().toString();

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private EBook book;

    @Column(nullable = false)
    private LocalDateTime purchaseDate;

    @Column(nullable = false)
    private int pricePaid;

    public Purchase() {}

    public Purchase(
            User user,
            EBook book,
            LocalDateTime purchaseDate,
            int pricePaid
    ) {
        this.user = user;
        this.book = book;
        this.purchaseDate = purchaseDate;
        this.pricePaid = pricePaid;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public EBook getBook() {
        return book;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public int getPricePaid() { return pricePaid; }
}