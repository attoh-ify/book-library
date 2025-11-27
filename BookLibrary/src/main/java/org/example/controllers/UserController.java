package org.example.controllers;

import org.example.dao.PurchaseDAO;
import org.example.dao.RentalDAO;
import org.example.dao.UserDAO;
import org.example.dtos.user.CreateUserRequest;
import org.example.dtos.user.UserResponse;
import org.example.filters.Secured;
import org.example.models.User;
import org.example.services.UserService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/booklib/users")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    private UserService userService;

    @Inject
    private UserDAO userDAO;

    @Inject
    private RentalDAO rentalDAO;

    @Inject
    private PurchaseDAO purchaseDAO;

    @POST
    public Response createUser(CreateUserRequest request) {
        User user = userService.createUser(request);
        UserResponse response = new UserResponse(user);
        return Response.ok(response).build();
    }

    @Path("/{email}")
    @GET
    @Secured
    public Response getUser(@PathParam("email") String email) {
        User user = userDAO.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("User not found");
        }
        return Response.ok(new UserResponse(user)).build();
    }

    @Path("/")
    @GET
    @Secured
    public Response getAllUsers() {
        List<User> users = userDAO.findAll();
        if (users == null) {
            throw new NotFoundException("User not found");
        }
        List<UserResponse> user_responses = new ArrayList<>();
        for (User user: users) {
            user_responses.add(new UserResponse(user));
        }
        return Response.ok(user_responses).build();
    }

    @Path("rental/{email}/")
    @GET
    @Secured
    public Response getUsersRentalHistory(@PathParam("email") String email) {
        return Response.ok(rentalDAO.rentalHistory(email)).build();
    }

    @Path("purchase/{email}/")
    @GET
    @Secured
    public Response getUsersPurchaseHistory(@PathParam("email") String email) {
        return Response.ok(purchaseDAO.purchaseHistory(email)).build();
    }
}