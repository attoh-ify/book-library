package org.example.services;

import org.example.dao.TokenDAO;
import org.example.models.SessionToken;
import org.example.models.User;
import org.example.utils.TimeUtils;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class TokenService {
    @Inject
    private TokenDAO tokenDAO;

    @Inject
    private TimeUtils timeUtils;

    public SessionToken issueToken(User user) {
        SessionToken session = new SessionToken(user, timeUtils.generateExpiry());
        tokenDAO.create(session);
        return session;
    }
}