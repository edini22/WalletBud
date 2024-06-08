package com.example.walletbud;


import jakarta.json.*;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import wb.walletbud.SystemInterface;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import java.io.StringReader;

@Path("/unica")
public class Unica {
    @POST
    @Path("/{tipo}/add")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUnicaUser(@PathParam("tipo") String tipo,String jsonString,@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
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
            String dateStr  = jsonObject.getString("date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
            Timestamp timestamp = Timestamp.valueOf(dateTime);
            
            String value_str = jsonObject.getString("value");
            float value = Float.parseFloat(value_str);

            int cond = SystemInterface.createUnica(name, value, descricao, local, tipo, IdCategoria, timestamp,email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " registada com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -1){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -3){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -4){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao encontrada!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else{
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
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @POST
    @Path("/{tipo}/set")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response setUnicaUser(@PathParam("tipo") String tipo,String jsonString,@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
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
            try {name = jsonObject.getString("name");} catch (java.lang.NullPointerException en) {name = null;}
            try {descricao = jsonObject.getString("descricao");} catch (java.lang.NullPointerException en) {descricao = null;}
            try {local = jsonObject.getString("local");} catch (java.lang.NullPointerException en) {local = null;}
            try {IdCategoria = jsonObject.getInt("IdCategoria");} catch (java.lang.NullPointerException en) {IdCategoria = -1;}
            try {dateStr  = jsonObject.getString("date");} catch (java.lang.NullPointerException en) {dateStr = null;}
            if (dateStr != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                LocalDateTime dateTime = LocalDateTime.parse(dateStr, formatter);
                timestamp = Timestamp.valueOf(dateTime);
            }else {
                timestamp = null;
            }

            String value_str;
            float value;
            try {value_str = jsonObject.getString("value");} catch (java.lang.NullPointerException en) {value_str = null;}
            if (value_str != null) value = Float.parseFloat(value_str);
            else value = -1;

            int cond = SystemInterface.editUnica(IdTransacao, name, value, descricao, local, tipo, IdCategoria, timestamp,email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", tipo + " editada com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -1){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -3){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -4){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao encontrada!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else{
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
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GET
    @Path("/{tipo}/list")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUnicasUser(@PathParam("tipo") String tipo,@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try{
            JsonObject unicas = SystemInterface.getUnicas(email, tipo);

            if (unicas.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhuma "+ tipo +" única encontrada!")
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
    public Response getUnica(@PathParam("tipo") String tipo,@PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        try {

            JsonObject unica = SystemInterface.getJsonUnicaById(id,email,tipo);

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
    public Response shareUnicaUser(String jsonString,@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();
        try {

            int IdTransacao = jsonObject.getInt("IdTransacao");
            JsonArray usersArray = jsonObject.getJsonArray("users");
            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                System.out.println("User Email: " + userEmail);
            }


            int cond = SystemInterface.shareUnica(IdTransacao, usersArray, email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Partilhado com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -1){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Algo de errado nao esta certo!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -2){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Ja partilhou com algum dos users!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -3){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else{
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
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }

    }

}
