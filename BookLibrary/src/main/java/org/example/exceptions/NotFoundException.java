package org.example.exceptions;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class NotFoundException extends AppException {
    public NotFoundException(String message, JsonObject data) {
        super(message, Response.Status.UNAUTHORIZED.getStatusCode(), data);
    }

    public NotFoundException(String message) {
        super(message, Response.Status.NOT_FOUND.getStatusCode());
    }
}