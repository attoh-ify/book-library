package org.example.exceptions;

import javax.ws.rs.core.Response;

public class NotFoundException extends AppException {
    public NotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND.getStatusCode());
    }
}