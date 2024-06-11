package com.walletbud;

import beans.stateless.GerirComentario;
import beans.stateless.GerirUtilizador;
import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
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
            int IdTransacao = jsonObject.getInt("IdTransacao");
            JsonArray comentarios = jsonObject.getJsonArray("comentarios");

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
            
            int status = gerirComentario.createComentario(comentarios, user, transacao);

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

    @POST
    @Path("/list")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response listComments(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        try {
            int IdTransacao = jsonObject.getInt("IdTransacao");

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

    @POST
    @Path("/remove")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteComment(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        try {
            int IdComentario = jsonObject.getInt("IdComentario");

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
}
