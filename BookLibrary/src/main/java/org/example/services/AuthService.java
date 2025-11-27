package org.example.services;

import org.example.dao.TokenDAO;
import org.example.dao.UserDAO;
import org.example.dtos.auth.LoginRequest;
import org.example.dtos.auth.LoginResponse;
import org.example.dtos.auth.LogoutResponse;
import org.example.exceptions.UnauthorizedException;
import org.example.models.SessionToken;
import org.example.models.User;
import org.example.utils.PasswordUtils;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class AuthService {
    @Inject
    private UserDAO userDAO;

    @Inject
    private TokenService tokenService;

    @Inject
    private TokenDAO tokenDAO;

    public LoginResponse login(LoginRequest req) {
        User user = userDAO.findByEmail(req.getEmail());

        if (user == null || !PasswordUtils.verifyPassword(req.getPassword(), user.getPasswordHash())) {
            throw new UnauthorizedException("Invalid email or password");
        }

        SessionToken token = tokenService.issueToken(user);

        return new LoginResponse(
                "SUCCESS",
                token.getToken(),
                token.getExpiry(),
                null,
                user
        );
    }

    public LogoutResponse logout(String authHeader) {
        String token = authHeader.substring("Bearer ".length());
        tokenDAO.deleteToken(token);
        return new LogoutResponse("SUCCESS", "Token valid");
    }
}