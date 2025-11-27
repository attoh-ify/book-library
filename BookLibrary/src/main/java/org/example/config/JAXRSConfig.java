package org.example.config;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/api")
public class JAXRSConfig extends Application {
    // Activates JAX-RS
    // Registers all REST endpoints automatically
    // Sets base path as /api
}