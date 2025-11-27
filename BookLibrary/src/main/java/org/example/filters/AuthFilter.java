package org.example.filters;

import org.example.dao.TokenDAO;
import org.example.exceptions.UnauthorizedException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Secured
@Provider
public class AuthFilter implements ContainerRequestFilter {
    @Inject
    private TokenDAO tokenDAO;

    @Override
    public void filter(ContainerRequestContext ctx) throws IOException {
        String authHeader = ctx.getHeaderString("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring("Bearer ".length());

        var session = tokenDAO.findByToken(token);

        if (session == null) {
            throw new UnauthorizedException("Invalid or expired session token");
        }

        ctx.setProperty("userId", session.getUserId());
    }
}