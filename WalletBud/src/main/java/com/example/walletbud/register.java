package com.example.walletbud;

import WB.walletbud.SystemInterface;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import java.io.StringReader;

@Path("/register")
public class register {

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(String jsonString) {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");


        if (username == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Invalid input\"}")
                    .build();
        }

        if (email == null || !email.contains("@")) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        try {
            SystemInterface.createUser(username, password, email);
        } catch (Exception e) {
            System.out.println("Error while creating user: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

        JsonObject jsonResponse = Json.createObjectBuilder()
                .add("message", "User registered successfully")
                .build();

        return Response.status(Response.Status.CREATED)
                .entity(jsonResponse.toString())
                .type(MediaType.APPLICATION_JSON)
                .build();

    }
}