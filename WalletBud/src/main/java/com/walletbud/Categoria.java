package com.walletbud;

import beans.stateless.GerirCategoria;
import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.StringReader;

@Path("/categoria")
public class Categoria {

//    @Inject
//    private UserBean userBean;

    @EJB
    private GerirCategoria gerirCategoria;

    @POST
    @Path("/add")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response AddCategoriaUser(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
//        System.out.println("email: " + email);
//
//        System.out.println("emailllll: " + userBean.getEmail());

        try{
            // Verificar se o email foi corretamente recuperado
            if (email == null) {
                // Se o email não estiver definido, retornar erro de não autorizado
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            JsonReader reader = Json.createReader(new StringReader(jsonString));
            JsonObject jsonObject = reader.readObject();
            reader.close();

            String name = jsonObject.getString("name");
            String tipo = jsonObject.getString("tipo");

            int cond = gerirCategoria.createCategoria(name, tipo, email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria criada com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -1){
                // categoria com esse nome ja existe
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria ja existe com esse nome!")
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

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }



    //GET todas as categorias de um user
    @GET
    @Path("/list")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCategorias(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try{
            JsonObject categorias = gerirCategoria.getCategorias(email);

            if (categorias.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhuma categoria encontrada!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(categorias.toString(), MediaType.APPLICATION_JSON).build();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    //GET de uma categoria apenas de um user
    @GET
    @Path("/get/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoria(@PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);
        try {

            JsonObject categoria = gerirCategoria.getJsonCategoriaById(id,email);

            if (categoria.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria não encontrada!")
                        .build();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }

            return Response.ok(categoria.toString(), MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @POST
    @Path("/set")
    @Secured
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response SetCategoriaUser(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        try{
            if (email == null) {
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            JsonReader reader = Json.createReader(new StringReader(jsonString));
            JsonObject jsonObject = reader.readObject();
            reader.close();

            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");

            int cond = gerirCategoria.editCategoria(id, name, email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria editada com sucesso!")
                        .build();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -1){
                // categoria com esse nome ja existe
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria ja existe com esse nome!")
                        .build();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -3){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao existe ou nao pertence a esse user!")
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
                    .add("message", "Formato invalido de Id!")
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
