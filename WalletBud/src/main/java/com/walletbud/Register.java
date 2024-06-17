package com.walletbud;

import beans.stateless.GerirUtilizador;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Path("/register")
public class Register {

    @EJB
    private GerirUtilizador gerirUtilizador;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    public static boolean validate(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(String jsonString) throws PersistentException {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        System.out.println("Received JSON: " + jsonObject.toString());

        String username = jsonObject.getString("username");
        String password = jsonObject.getString("password");
        String email = jsonObject.getString("email");


        if (username == null || password == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("{\"message\":\"Invalid input\"}")
                    .build();
        }

        if (!validate(email)) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Email address is invalid")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        }

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            if(gerirUtilizador.createUser(session,username, password, email) ){

                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "User registered successfully")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }else{
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email Já registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            System.out.println("Error while creating user: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}