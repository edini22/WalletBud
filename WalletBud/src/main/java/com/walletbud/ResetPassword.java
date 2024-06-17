package com.walletbud;

import beans.stateless.GerirUtilizador;
import jakarta.ejb.EJB;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;
import wb.walletbud.User;

import java.io.*;


@Path("/reset_password")
public class ResetPassword {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @Path("/send")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response sendRecoveryEmail(String jsonString) throws PersistentException {

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            String email = jsonObject.getString("email");

            User user = gerirUtilizador.getUserByEmail(session, email);


            if (user == null) {
                transaction.rollback();
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Recuperação de password falhou!")
                        .build();
                System.out.println(jsonResponse.toString());
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            System.out.println("User:" + user.getName());

            int cond = gerirUtilizador.sendRecoveryToken(session, user);



            if (cond == 0) {
                transaction.commit();
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Envio bem sucedido!")
                        .build();
                return Response.status(Response.Status.OK).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                transaction.rollback();
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Envio falhou!")
                        .build();
                System.out.println(jsonResponse.toString());
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            if( transaction != null)
                transaction.rollback();
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Envio falhou!")
                    .build();
            System.out.println(jsonResponse.toString());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

    @Path("/set")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response passwordRecovery(String jsonString) throws PersistentException {

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            String email = jsonObject.getString("email");
            String token = jsonObject.getString("token");
            String password = jsonObject.getString("password");

            User user = gerirUtilizador.getUserByEmail(session, email);

            if (user == null) {
                transaction.rollback();
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Recuperação de password falhou!")
                        .build();
                System.out.println(jsonResponse.toString());
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            int cond = gerirUtilizador.resetPassword(session, user, token, password);

            if (cond == 0) {
                transaction.commit();
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Recuperação de password bem sucedida!")
                        .build();
                return Response.status(Response.Status.OK).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                transaction.rollback();
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Recuperação de password falhou!")
                        .build();
                System.out.println(jsonResponse.toString());
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            if( transaction != null)
                transaction.rollback();
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Recuperação de password falhou!")
                    .build();
            System.out.println(jsonResponse.toString());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

    }

}
