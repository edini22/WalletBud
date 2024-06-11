package com.walletbud;


import beans.stateless.GerirFixa;
import beans.stateless.GerirUnica;
import jakarta.ejb.EJB;
import jakarta.json.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.StringReader;

@Path("/{transacao}")
public class Transacao {

    @EJB
    private GerirUnica gerirUnica;

    @EJB
    private GerirFixa gerirFixa;

    @POST
    @Path("/{tipo}/add")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addTransaction(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        try {
            String name = jsonObject.getString("name");
            String descricao = jsonObject.getString("descricao");
            String local = jsonObject.getString("local");
            int IdCategoria = jsonObject.getInt("IdCategoria");
            String dateStr = jsonObject.getString("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            JsonArray usersArray = jsonObject.getJsonArray("users");
            String comentario = jsonObject.getString("comentario");

            String value_str = jsonObject.getString("value");
            float value = Float.parseFloat(value_str);
            int cond = -1;

            if (transacao.equals("fixa")) {
                int repeticao = jsonObject.getInt("repeticao");

                cond = gerirFixa.createFixa(name, value, descricao, local, tipo, IdCategoria, timestamp, repeticao, email, usersArray, comentario);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.createUnica(name, value, descricao, local, tipo, IdCategoria, timestamp, email, usersArray, comentario);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " registada com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao encontrada!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria!")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("/{tipo}/set")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setTransaction(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        try {
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

                cond = gerirFixa.editFixa(IdTransacao, name, value, descricao, local, tipo, IdCategoria, timestamp, repeticao, email);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.editUnica(IdTransacao, name, value, descricao, local, tipo, IdCategoria, timestamp, email);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " editada com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao encontrada!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria ou do IdTransacao!")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("/{tipo}/list")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listTransactionsUser(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try {
            JsonObject unicas;
            if (transacao.equals("fixa")) {
                unicas = gerirFixa.getFixas(email, tipo);
            } else if (transacao.equals("unica")) {
                unicas = gerirUnica.getUnicas(email, tipo);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (unicas.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhuma " + tipo + " única encontrada!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(unicas.toString(), MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("/{tipo}/get/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransaction(@PathParam("transacao") String transacao, @PathParam("tipo") String tipo, @PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        try {
            JsonObject unica;
            if (transacao.equals("fixa")) {
                unica = gerirFixa.getJsonFixaById(id, email, tipo);
            } else if (transacao.equals("unica")) {
                unica = gerirUnica.getJsonUnicaById(id, email, tipo);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (unica.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " única não encontrada!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(unica.toString(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/share")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response shareTransaction(@PathParam("transacao") String transacao, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        try {

            int IdTransacao = jsonObject.getInt("IdTransacao");
            int option = jsonObject.getInt("option");
            String email_shared = jsonObject.getString("email_shared");

            if (email.equals(email_shared)) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao pode partilhar consigo mesmo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            int cond = -1;

            if (transacao.equals("fixa")) {
                cond = gerirFixa.editUsersFixa(email, IdTransacao, option, email_shared);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.editUsersUnica(email, IdTransacao, option, email_shared);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
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
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao tem permissoes para fazer tais operações!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "erro indefinido!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria ou do IdTransacao!")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("/confirm")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response confirmSharedTransaction(@PathParam("transacao") String transacao, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        try {

            int IdTransacao = jsonObject.getInt("IdTransacao");
            int option = jsonObject.getInt("option");

            int cond = -1;

            if (transacao.equals("fixa")) {
                cond = gerirFixa.handleFixa(email, IdTransacao, option);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.handleUnica(email, IdTransacao, option);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
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
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode recusar uma transacao já confirmada!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode remover um utilizador não associado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de IdCategoria ou do IdTransacao!")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("/pay")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response payTransaction(@PathParam("transacao") String transacao, String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        try {

            int IdTransacao = jsonObject.getInt("IdTransacao");
            String dateNow = jsonObject.getString("dateNow");
            String date = jsonObject.getString("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateNow, formatter);
            Timestamp time_dateNow = Timestamp.valueOf(dateTime);

            dateTime = LocalDateTime.parse(date, formatter);
            Timestamp time_date = Timestamp.valueOf(dateTime);


            int cond = -1;

            if (transacao.equals("fixa")) {
                cond = gerirFixa.payFixa(email, IdTransacao, time_dateNow, time_date);
            }else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (cond == 0) {
                JsonObject jsonResponse = null;

                jsonResponse = Json.createObjectBuilder()
                        .add("message", "Transacao paga com sucesso!")
                        .build();

                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode pagar uma transacao que nao seja o criador!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Não pode pagar pagar uma transacao nao confirmada!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido do IdTransacao!")
                    .build();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("/payments")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTransactionPay(@PathParam("transacao") String transacao, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        try {
            JsonObject payments;
            if (transacao.equals("fixa")) {
                payments = gerirFixa.getJsonPaymentFixa(email);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            if (payments.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Transacoes não encontradas!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(payments.toString(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{idTransacao}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("idTransacao") int idTransacao, @PathParam("transacao") String transacao, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try{
            int cond;
            if (transacao.equals("fixa")) {
                cond = gerirFixa.giveUpTransactionFixa(email,idTransacao);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.giveUpTransactionUnica(email,idTransacao);
            } else {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Tipo de Transacao nao existe!")
                        .build();
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

                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -1) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -2) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao pode eliminar uma transacao unica sem ser o criador!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -3) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if (cond == -4) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao pode desistir de uma transacao ao qual nao pertence!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else {
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

}
