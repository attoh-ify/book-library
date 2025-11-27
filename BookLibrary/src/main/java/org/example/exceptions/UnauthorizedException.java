package org.example.exceptions;

import javax.ws.rs.core.Response;

public class UnauthorizedException extends AppException {
    public UnauthorizedException(String message) {
        super(message, Response.Status.UNAUTHORIZED.getStatusCode());
    }
}