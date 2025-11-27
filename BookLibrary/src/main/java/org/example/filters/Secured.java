package org.example.filters;

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@NameBinding  // JAX-RS feature that binds filters to annotated methods/classes.
@Retention(RetentionPolicy.RUNTIME)  // ensures it’s available at runtime, so filters can detect it.
public @interface Secured {}