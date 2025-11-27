package org.example.controllers;

import org.example.dao.RentalDAO;
import org.example.dtos.rental.RentalRequest;
import org.example.dtos.rental.RentalResponse;
import org.example.dtos.rental.ReturnBookRequest;
import org.example.dtos.rental.ReturnBookResponse;
import org.example.filters.Secured;
import org.example.services.RentalService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/booklib/rentals")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RentalController {
    @Inject
    private RentalService rentalService;

    @Inject
    private RentalDAO rentalDAO;

    @POST
    @Secured
    @Path("/rent")
    public Response rentBook(RentalRequest request) {
        RentalResponse rentalResponse = rentalService.rentBook(request);
        return Response.ok(rentalResponse).build();
    }

    @POST
    @Secured
    @Path("/return")
    public Response returnBook(ReturnBookRequest request) {
        ReturnBookResponse returnBookResponse = rentalService.returnBook(request);
        return Response.ok(returnBookResponse).build();
    }

    @GET
    @Secured
    @Path("/{email}")
    public Response getAllRentals(@PathParam("email") String email) {
        return Response.ok(rentalDAO.rentalHistory(email)).build();
    }
}
