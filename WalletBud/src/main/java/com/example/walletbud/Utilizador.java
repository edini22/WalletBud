package com.example.walletbud;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import wb.walletbud.SystemInterface;

@Path("/user")
public class Utilizador {

    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try{
            JsonObject user = SystemInterface.getJsonUserInfo(email);

            if (user.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhum utilizador encontrado!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(user.toString(), MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/get/{email}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@PathParam("email") String email_user, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try{
            JsonObject user = SystemInterface.getJsonUserInfo(email_user);

            if (user.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhum utilizador encontrado!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Utilizador encontrado!")
                    .build();
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
