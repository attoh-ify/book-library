package org.example.exceptions;

import javax.json.JsonObject;
import javax.ws.rs.core.Response;

public class BadRequestException extends AppException {
    public BadRequestException(String message, JsonObject data) {
        super(message, Response.Status.UNAUTHORIZED.getStatusCode(), data);
    }

    public BadRequestException(String message) {
        super(message, Response.Status.BAD_REQUEST.getStatusCode());
    }
}