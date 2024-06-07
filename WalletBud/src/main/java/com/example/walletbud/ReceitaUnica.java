package com.example.walletbud;


import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import wb.walletbud.SystemInterface;

import java.io.StringReader;

@Path("/receitaunica")
public class ReceitaUnica {
    @POST
    @Path("/add")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response ReceitaUnicaUser(String jsonString,@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        reader.close();

        try {
            String name = jsonObject.getString("name");
            String value_str = jsonObject.getString("value");
            String descricao = jsonObject.getString("descricao");
            String local = jsonObject.getString("local");
            String tipo = "receita";
            int IdCategoria = jsonObject.getInt("IdCategoria");
            String date  = jsonObject.getString("date");

            float value = Float.parseFloat(value_str);

            int cond = SystemInterface.createReceitaUnica(name, value, descricao, local, tipo, IdCategoria, email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Receita registada com sucesso!")
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
}
