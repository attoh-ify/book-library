package org.example.exceptions;

import javax.json.Json;
import javax.json.JsonObject;

public class AppException extends RuntimeException {
    private final int statusCode;
    private final JsonObject data;

    public AppException(String message, int statusCode, JsonObject data) {
        super(message);
        this.statusCode = statusCode;
        this.data = data;
    }

    public AppException(String message, int statusCode) {
        super(message);
        this.statusCode = statusCode;
        this.data = Json.createObjectBuilder().build();
    }

    public int getStatusCode() {
        return statusCode;
    }

    public JsonObject getData() {
        return data;
    }
}