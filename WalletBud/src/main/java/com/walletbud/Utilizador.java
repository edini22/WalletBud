package com.walletbud;

import jakarta.ejb.EJB;
import beans.stateless.GerirUtilizador;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;

import java.io.StringReader;

@Path("/user")
public class Utilizador {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @GET
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        PersistentSession session = null;
        PersistentTransaction transaction = null;
        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();
            JsonObject user = gerirUtilizador.getJsonUserInfo(session, email);
            transaction.commit();
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
            if( transaction != null)
                transaction.rollback();
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @GET
    @Path("/get/{email}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserInfo(@PathParam("email") String email_user, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject user = gerirUtilizador.getJsonUserInfo(session, email_user);
            transaction.commit();
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
            if( transaction != null)
                transaction.rollback();
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @POST
    @Path("/set")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editUser(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email_user = JWTUtil.getEmailFromToken(token);


        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        System.out.println("Received JSON: " + jsonObject.toString());

        String username, password, idioma,new_email;
        Float objetivo;

        try{username = jsonObject.getString("username");}catch(java.lang.NullPointerException en) {username=null;}
        try{password = jsonObject.getString("password");}catch(java.lang.NullPointerException en) {password=null;}
        try{idioma = jsonObject.getString("idioma");}catch(java.lang.NullPointerException en) {idioma=null;}
        try{new_email = jsonObject.getString("email");}catch(java.lang.NullPointerException en) {new_email=null;}
        try{objetivo = Float.parseFloat(jsonObject.getString("objetivo"));}catch(java.lang.NullPointerException en) {objetivo=null;}

        if(new_email != null){
            if(new_email.equals(email_user)){
                new_email = null;
            }
        }

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            if(gerirUtilizador.editUser(session, username, password, email_user, idioma, new_email, objetivo) ){

                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "User edited successfully")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }else{
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            if( transaction != null)
                transaction.rollback();
            System.out.println("Error while creating user: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @GET
    @Path("/getNotif/{email}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserNotifs(@PathParam("email") String email_user, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject notifsUser = gerirUtilizador.getJsonUserNotifs(session,email_user);

            if (notifsUser.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhum utilizador encontrado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Utilizador encontrado!")
                    .add("notificacoes", notifsUser)
                    .build();
            return Response.ok(jsonResponse, MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            if( transaction != null)
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
