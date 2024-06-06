package com.example.walletbud;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@Path("/hello-world")
public class HelloResource {
    @GET
    @Secured
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
}