package org.example.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
//  It’s annotated with @Provider, so it’s automatically applied to all endpoints
public class LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        System.out.println(
                "[REQUEST] -> " + requestContext.getMethod() + " "
                + requestContext.getUriInfo().getPath() + " | From: "
                + requestContext.getHeaderString("X-Forward-FOr")
        );
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        System.out.println(
                "[RESPONSE] <- " + requestContext.getMethod() + " " + requestContext.getUriInfo().getPath()
                + " | Status: " + responseContext.getStatus()
        );
    }
}