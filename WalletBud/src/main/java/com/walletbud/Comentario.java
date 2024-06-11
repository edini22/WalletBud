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
    public Response addComment(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        try {
            int IdTransacao = jsonObject.getInt("idTransacao");
            String comentario = jsonObject.getString("comentario");

            User user = gerirUtilizador.getUserByEmail(email);

            Transacao transacao = TransacaoDAO.getTransacaoByORMID(IdTransacao);
            if (transacao == null) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "A transacao nao existe!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
            int status = 0;
            try {
                status = gerirComentario.createComentario(comentario, user, transacao);
                t.commit();
            }
            catch (PersistentException pe) {
                t.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

            if (status == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Comentario(s) criado(s) com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/list/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listComments(@PathParam("id") int IdTransacao, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        System.out.println("Email: " + email);
        try {

            JsonObject comentarios = gerirComentario.getComentariosByTransaction(IdTransacao, email);

            if (comentarios.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(comentarios.toString(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @DELETE
    @Path("/remove/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(@PathParam("id") int IdComentario, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try {
            int status = gerirComentario.deleteComentario(IdComentario, email);

            if (status == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Comentario removido com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("/edit")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response editComment(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        try {
            int IdComentario = jsonObject.getInt("IdComentario");
            String descricao = jsonObject.getString("descricao");

            int status = gerirComentario.editComentario(descricao, email, IdComentario);

            if (status == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Comentario editado com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }
}
