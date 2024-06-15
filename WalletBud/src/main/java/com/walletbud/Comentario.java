package com.walletbud;

import beans.stateless.GerirComentario;
import beans.stateless.GerirUtilizador;
import jakarta.ejb.EJB;
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
import wb.walletbud.Transacao;
import wb.walletbud.TransacaoDAO;
import wb.walletbud.User;

import java.io.StringReader;

@Path("/comment")
public class Comentario {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @EJB
    private GerirComentario gerirComentario;

    @POST
    @Path("/add")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addComment(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        PersistentSession session = null;
        PersistentTransaction transaction = null;
        
        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            int IdTransacao = jsonObject.getInt("idTransacao");
            String comentario = jsonObject.getString("comentario");

            User user = gerirUtilizador.getUserByEmail(session, email);

            if(user == null){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "User nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            Transacao transacao = TransacaoDAO.getTransacaoByORMID(session, IdTransacao);
            if (transacao == null) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "A transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            int status = gerirComentario.createComentario(session, comentario, user, transacao);

            if (status == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Comentario(s) criado(s) com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @GET
    @Path("/list/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listComments(@PathParam("id") int IdTransacao, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        System.out.println("Email: " + email);
        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject comentarios = gerirComentario.getComentariosByTransaction(session, IdTransacao, email);

            if (comentarios.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(comentarios.toString(), MediaType.APPLICATION_JSON).build();
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

    @DELETE
    @Path("/remove/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") int IdComentario, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            int status = gerirComentario.deleteComentario(session, IdComentario, email);

            if (status == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Comentario removido com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

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
    @Path("/edit")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editComment(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            int IdComentario = jsonObject.getInt("IdComentario");
            String descricao = jsonObject.getString("descricao");

            int status = gerirComentario.editComentario(session, descricao, email, IdComentario);

            if (status == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Comentario editado com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

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
}
