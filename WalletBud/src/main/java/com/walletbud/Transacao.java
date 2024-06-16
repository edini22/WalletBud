package com.walletbud;


import beans.stateless.GerirFixa;
import beans.stateless.GerirTransacaoPartilhada;
import beans.stateless.GerirUnica;
import jakarta.ejb.EJB;
import jakarta.json.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.StringReader;
@Path("")
public class Transacao {

    @EJB
    private GerirUnica gerirUnica;

    @EJB
    private GerirFixa gerirFixa;

    @EJB
    private GerirTransacaoPartilhada gerirTransacaoPartilhada;

    @POST
    @Path("/{transacao}/{tipo}/add")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTransaction(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
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

            String name = jsonObject.getString("name");
            String descricao = null;
            try {
                JsonValue comentarioValue = jsonObject.get("descricao");
                if (comentarioValue instanceof JsonString) {
                    descricao = ((JsonString) comentarioValue).getString();
                }
            } catch (NullPointerException ignored) {
            }
            String local = jsonObject.getString("local");
            int IdCategoria = jsonObject.getInt("IdCategoria");
            String dateStr = jsonObject.getString("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            JsonArray usersArray = jsonObject.getJsonArray("users");
            System.out.println(usersArray.toString());
            String comentario = null;
            try {
                JsonValue comentarioValue = jsonObject.get("comentario");
                if (comentarioValue instanceof JsonString) {
                    comentario = ((JsonString) comentarioValue).getString();
                }
            } catch (NullPointerException ignored) {
            }

            String value_str = jsonObject.getString("value");
            float value = Float.parseFloat(value_str);
            int cond = -1;

            if (transacao.equals("fixa")) {
                int repeticao = jsonObject.getInt("repeticao");

                cond = gerirFixa.createFixa(session, name, value, descricao, local, tipo, IdCategoria, timestamp, repeticao, email, usersArray, comentario);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.createUnica(session, name, value, descricao, local, tipo, IdCategoria, timestamp, email, usersArray, comentario);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction .rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " registada com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao encontrada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }else if (cond == -5) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algum user que inseriu ja pertence ou nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }  else {
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria!")
                    .build();
            if( transaction != null)
                transaction.rollback();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
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
    @Path("/{transacao}/{tipo}/set")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setTransaction(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
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

            String name, descricao, local, dateStr;
            int IdCategoria;
            Timestamp timestamp;

            int IdTransacao = jsonObject.getInt("IdTransacao");
            try {
                name = jsonObject.getString("name");
            } catch (java.lang.NullPointerException en) {
                name = null;
            }
            try {
                descricao = jsonObject.getString("descricao");
            } catch (java.lang.NullPointerException en) {
                descricao = null;
            }
            try {
                local = jsonObject.getString("local");
            } catch (java.lang.NullPointerException en) {
                local = null;
            }
            try {
                IdCategoria = jsonObject.getInt("IdCategoria");
            } catch (java.lang.NullPointerException en) {
                IdCategoria = -1;
            }
            try {
                dateStr = jsonObject.getString("date");
            } catch (java.lang.NullPointerException en) {
                dateStr = null;
            }
            if (dateStr != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                timestamp = Timestamp.valueOf(dateTime);
            } else {
                timestamp = null;
            }

            String value_str;
            float value;
            try {
                value_str = jsonObject.getString("value");
            } catch (java.lang.NullPointerException en) {
                value_str = null;
            }
            if (value_str != null) value = Float.parseFloat(value_str);
            else value = -1;

            int cond = -1;

            if (transacao.equals("fixa")) {
                int repeticao;
                try {
                    repeticao = jsonObject.getInt("repeticao");
                } catch (java.lang.NullPointerException en) {
                    repeticao = -1;
                }

                cond = gerirFixa.editFixa(session, IdTransacao, name, value, descricao, local, tipo, IdCategoria, timestamp, repeticao, email);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.editUnica(session, IdTransacao, name, value, descricao, local, tipo, IdCategoria, timestamp, email);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " editada com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao encontrada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria ou do IdTransacao!")
                    .build();
            if( transaction != null)
                transaction.rollback();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
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
    @Path("/{transacao}/{tipo}/list")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTransactionsUser(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject unicas;
            if (transacao.equals("fixa")) {
                unicas = gerirFixa.getFixas(session, email, tipo);
            } else if (transacao.equals("unica")) {
                unicas = gerirUnica.getUnicas(session, email, tipo);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (unicas.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhuma " + tipo + " única encontrada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(unicas.toString(), MediaType.APPLICATION_JSON).build();

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
    @Path("/{transacao}/{tipo}/get/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, @PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject unica;
            if (transacao.equals("fixa")) {
                unica = gerirFixa.getJsonFixaById(session, id, email, tipo);
            } else if (transacao.equals("unica")) {
                unica = gerirUnica.getJsonUnicaById(session, id, email, tipo);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (unica.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " única não encontrada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(unica.toString(), MediaType.APPLICATION_JSON).build();
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
    @Path("/{transacao}/share")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response shareTransaction(@PathParam("transacao") String transacao, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
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

            int IdTransacao = jsonObject.getInt("IdTransacao");
            int option = jsonObject.getInt("option");
            String email_shared = jsonObject.getString("email_shared");

            if (email.equals(email_shared)) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao pode partilhar consigo mesmo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            int cond = -1;

            if (transacao.equals("fixa")) {
                cond = gerirFixa.editUsersFixa(session, email, IdTransacao, option, email_shared);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.editUsersUnica(session, email, IdTransacao, option, email_shared);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
                transaction.commit();
                JsonObject jsonResponse = null;
                if (option == 1) {
                    jsonResponse = Json.createObjectBuilder()
                            .add("message", "Transacao partilhada confirmada com sucesso!")
                            .build();
                } else if (option == -1) {
                    jsonResponse = Json.createObjectBuilder()
                            .add("message", "Transacao partilhada recusada com sucesso!")
                            .build();
                }
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao tem permissoes para fazer tais operações!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "erro indefinido!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria ou do IdTransacao!")
                    .build();
            if( transaction != null)
                transaction.rollback();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
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
    @Path("/{transacao}/confirm")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmSharedTransaction(@PathParam("transacao") String transacao, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
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

            int IdTransacao = jsonObject.getInt("IdTransacao");
            int option = jsonObject.getInt("option");

            int cond = -1;

            if (transacao.equals("fixa")) {
                cond = gerirFixa.handleFixa(session, email, IdTransacao, option);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.handleUnica(session, email, IdTransacao, option);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
                JsonObject jsonResponse = null;
                transaction.commit();
                if (option == 1) {
                    jsonResponse = Json.createObjectBuilder()
                            .add("message", "Transacao partilhada confirmada com sucesso!")
                            .build();
                } else if (option == -1) {
                    jsonResponse = Json.createObjectBuilder()
                            .add("message", "Transacao partilhada recusada com sucesso!")
                            .build();
                }
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode recusar uma transacao já confirmada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode remover um utilizador não associado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria ou do IdTransacao!")
                    .build();
            if( transaction != null)
                transaction.rollback();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
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
    @Path("/fixa/pay")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response payTransaction( String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
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

            int IdTransacao = jsonObject.getInt("IdTransacao");
            String date = jsonObject.getString("date");

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            Timestamp time_dateNow = new Timestamp(System.currentTimeMillis());

            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            Timestamp time_date = Timestamp.valueOf(dateTime);

            int cond = gerirFixa.payFixa(session, email, IdTransacao, time_dateNow, time_date);

            if (cond == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Transacao paga com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode pagar uma transacao que nao seja o criador!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode pagar pagar uma transacao nao confirmada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido do IdTransacao!")
                    .build();
            if( transaction != null)
                transaction.rollback();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
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
    @Path("/{transacao}/payments")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionPay(@PathParam("transacao") String transacao, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject payments;
            if (transacao.equals("fixa")) {
                payments = gerirFixa.getJsonPaymentFixa(session, email);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (payments.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Transacoes não encontradas!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(payments.toString(), MediaType.APPLICATION_JSON).build();
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
    @Path("/{transacao}/{idTransacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("idTransacao") int idTransacao, @PathParam("transacao") String transacao, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            int cond;
            if (transacao.equals("fixa")) {
                cond = gerirFixa.giveUpTransactionFixa(session, email,idTransacao);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.giveUpTransactionUnica(session, email,idTransacao);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            if (cond == 0) {
                JsonObject jsonResponse = null;

                jsonResponse = Json.createObjectBuilder()
                        .add("message", "Transacao removida com sucesso!")
                        .build();
                transaction.commit();

                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao pode eliminar uma transacao unica sem ser o criador!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao pode desistir de uma transacao ao qual nao pertence!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e){
            if( transaction != null)
                transaction.rollback();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    @GET
    @Path("/movimentos") //movimentos todos feitos pelo user
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMovimentos( @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject movimentos;
            movimentos = gerirTransacaoPartilhada.getMovimentos(session,email);
            if (movimentos.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(movimentos.toString(), MediaType.APPLICATION_JSON).build();
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
    @Path("/pendentes")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPendentes( @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject movimentos;
            movimentos = gerirTransacaoPartilhada.getPendentes(session, email);
            if (movimentos.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            System.out.println("return" + movimentos.toString());
            return Response.ok(movimentos.toString(), MediaType.APPLICATION_JSON).build();
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
    @Path("/movimentos/{dias}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listMovimentos(@PathParam("dias") int dias, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject movimentos;
            movimentos = gerirTransacaoPartilhada.getMovimentosDays(session, email, dias);
            if (movimentos.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "User nao encontrado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(movimentos.toString(), MediaType.APPLICATION_JSON).build();
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
    @Path("/fixa/transacoesAtraso") //pagamentos/recibos em atraso das transacoes fixas
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTransacoesAtraso( @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject leite =  gerirFixa.getLatePayments(session, email);


            if (leite.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(leite.toString(), MediaType.APPLICATION_JSON).build();
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
    @Path("/fixa/pagamentos") //pagamentos/recibos em atraso das transacoes fixas
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listPagamentos( @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject leite =  gerirFixa.getPayments(session, email);


            if (leite.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(leite.toString(), MediaType.APPLICATION_JSON).build();
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

    @GET//Em processo de construcao
    @Path("/timeline/{ano}/{mes}") //timeline da do mes em que esta o user das transacoes! | deve ter que levar o mes e ano pela rota
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTimeline(@PathParam("ano") int ano, @PathParam("mes") int mes, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        if(mes < 1 || mes > 12){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject timeline = gerirTransacaoPartilhada.getTimeline(session, email,ano,mes);

            if (timeline.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(timeline.toString(), MediaType.APPLICATION_JSON).build();

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

    @GET//Em processo de construcao
    @Path("/gastosmes/{ano}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listGastosPorMes(@PathParam("ano") int ano, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject gastos = gerirTransacaoPartilhada.getGastosPorMes(session, email, ano);

            if (gastos.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(gastos.toString(), MediaType.APPLICATION_JSON).build();

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
