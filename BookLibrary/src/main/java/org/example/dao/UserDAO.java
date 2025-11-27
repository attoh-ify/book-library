package org.example.dao;

import org.example.models.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class UserDAO {
    @PersistenceContext(unitName = "BOOKLIBUnit")
    private EntityManager em;

    public void create(User user) {
        em.persist(user);
    }

    public User update(User user) {
        return em.merge(user);
    }

    public User findById(String id) {
        return em.find(User.class, id);
    }

    public User findByEmail(String email) {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.email = :email", User.class
        );
        query.setParameter("email", email);
        List<User> result = query.getResultList();
        return result != null ? result.get(0) : null;
    }

    public void delete(String id) {
        User user = em.find(User.class, id);
        if (user != null) {
            em.remove(user);
        }
    }

    public List<User> findAll() {
        TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u", User.class
        );
        return query.getResultList();
    }

    public boolean exists(String email) {
        User user = em.find(User.class, email);
        return user != null;
    }
}