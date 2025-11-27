package org.example.dao;

import org.example.models.Book;
import org.example.models.Enums;
import org.example.models.PhysicalBook;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class BookDAO {
    @PersistenceContext(unitName = "BOOKLIBUnit")
    EntityManager em;

    public void add(Book book) {
        em.persist(book);
    }

    public Book update(Book book) {
        return em.merge(book);
    }

    public Book findById(String id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b", Book.class
        );
        return query.getResultList();
    };

    public List<Book> findByAuthor(String author) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.author = :author", Book.class
        );
        query.setParameter("author", author);
        return query.getResultList();
    };

    public List<Book> findByGenre(Enums.GENRES genre) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.genre = :genre", Book.class
        );
        query.setParameter("genre", genre);
        return query.getResultList();
    };

    public List<Book> findByFormat(Enums.BOOK_FORMAT format) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.format = :format", Book.class
        );
        query.setParameter("format", format);
        return query.getResultList();
    };

    public Book findByIsbn(String isbn) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class
        );
        query.setParameter("isbn", isbn);
        List<Book> result = query.getResultList();
        return result != null ? result.get(0) : null;
    };

    public List<Book> findByTitle(String title) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.title = :title", Book.class
        );
        query.setParameter("title", title);
        return query.getResultList();
    }

    public void remove(String isbn) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class
        );
        query.setParameter("isbn", isbn);
        List<Book> result = query.getResultList();
        Book book = result.get(0);
        if (!em.contains(book)) {
            em.merge(book);
        }
        em.remove(book);
    };

    public int bookCount(String isbn) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class
        );
        query.setParameter("isbn", isbn);
        List<Book> result = query.getResultList();
        Book book = result.get(0);
        if (book instanceof PhysicalBook) {
            return ((PhysicalBook) book).getQuantityAvailable();
        }
        return 0;
    }

    public boolean isbnIsUnique(String isbn) {
        TypedQuery<Book> query = em.createQuery(
                "SELECT b FROM Book b WHERE b.isbn = :isbn", Book.class
        );
        query.setParameter("isbn", isbn);
        List<Book> result = query.getResultList();
        return result != null;
    }

    public void incrementBookCount(String isbn) {
        Query query = em.createQuery(
                "UPDATE PhysicalBook b SET b.quantityAvailable = b.quantityAvailable + 1 WHERE b.isbn = :isbn"
        );
        query.setParameter("isbn", isbn);
        query.executeUpdate();
    }

    public void decrementBookCount(String isbn) {
        Query query = em.createQuery(
                "UPDATE PhysicalBook b SET b.quantityAvailable = b.quantityAvailable - 1 WHERE b.isbn = :isbn AND b.quantityAvailable > 0"
        );
        query.setParameter("isbn", isbn);
        query.executeUpdate();
    }
}
