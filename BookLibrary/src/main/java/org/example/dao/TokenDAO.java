package org.example.dao;

import org.example.models.SessionToken;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.time.LocalDateTime;
import java.util.List;

@Stateless
public class TokenDAO {
    @PersistenceContext(unitName = "BOOKLIBUnit")
    private EntityManager em;

    public void create(SessionToken token) {
        em.persist(token);
    }

    public SessionToken findByToken(String tokenStr) {
        TypedQuery<SessionToken> query = em.createQuery(
                "SELECT t FROM SessionToken t WHERE t.token = :token AND t.expiry > :now", SessionToken.class
        );
        query.setParameter("token", tokenStr);
        query.setParameter("now", LocalDateTime.now());
        List<SessionToken> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    public void deleteToken(String tokenStr) {
        SessionToken token = findByToken(tokenStr);
        if (token != null) {
            em.remove(token);
        }
    }
}