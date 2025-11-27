package org.example.controllers;

import org.example.dao.PurchaseDAO;
import org.example.dtos.purchase.PurchaseRequest;
import org.example.dtos.purchase.PurchaseResponse;
import org.example.filters.Secured;
import org.example.services.PurchaseService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/booklib/purchases")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PurchaseController {
    @Inject
    private PurchaseService purchaseService;

    @Inject
    private PurchaseDAO purchaseDAO;

    @POST
    @Secured
    @Path("/buy")
    public Response purchaseBook(PurchaseRequest request) {
        PurchaseResponse purchaseResponse = purchaseService.buyEBook(request);
        return Response.ok(purchaseResponse).build();
    }

    @GET
    @Secured
    @Path("/{email}")
    public Response getAllPurchases(@PathParam("email") String email) {
        return Response.ok(purchaseDAO.purchaseHistory(email)).build();
    }
}
