package com.example.walletbud;

import jakarta.inject.Inject;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import wb.walletbud.SystemInterface;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Date;

import java.io.StringReader;

@Path("/login")
public class login {

//    @Inject
//    private UserBean userBean;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginUser(String jsonString) {

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        System.out.println("Received JSON: " + jsonObject.toString());

        String email = jsonObject.getString("email");
        String password = jsonObject.getString("password");

        try {
            int cond = SystemInterface.verifyUser(email,password);
            if (cond == 0){
                // Gerar token JWT
                String token = generateToken(email);

                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("token", token)
                        .build();

//                // Armazenar email no UserBean
//                userBean.setEmail(email);
                System.out.println(jsonResponse.toString());
                return Response.status(Response.Status.OK)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();

            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "password does not match!")
                        .build();
                System.out.println(jsonResponse.toString());
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email does not exist!")
                        .build();
                System.out.println(jsonResponse.toString());
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return Response.ok("Hello World!").build();
    }
    private String generateToken(String email) {
        Algorithm algorithm = Algorithm.HMAC256("secret");

        Date expiresAt = new Date(System.currentTimeMillis() + 3600 * 1000);

        // Gera o token
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(expiresAt)
                .sign(algorithm);
    }
}
