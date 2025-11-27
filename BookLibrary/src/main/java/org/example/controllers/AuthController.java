package org.example.controllers;

import org.example.dao.TokenDAO;
import org.example.dtos.auth.LoginRequest;
import org.example.dtos.auth.LoginResponse;
import org.example.dtos.auth.LogoutResponse;
import org.example.filters.Secured;
import org.example.services.AuthService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/booklib/auth")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AuthController {
    @Inject
    private AuthService authService;

    @Inject
    private TokenDAO tokenDAO;

    @POST
    @Path("/login")
    public Response login(LoginRequest request) {
        LoginResponse loginResponse = authService.login(request);
        return Response.ok(loginResponse).build();
    }

    @DELETE
    @Path("/logout")
    @Secured
    public Response logout(@HeaderParam("Authorization") String authHeader) {
        LogoutResponse logoutResponse = authService.logout(authHeader);
        return Response.ok(logoutResponse).build();
    }
}