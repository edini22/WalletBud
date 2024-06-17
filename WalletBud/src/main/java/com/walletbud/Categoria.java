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
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;

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
    public Response AddCategoriaUser(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            // Verificar se o email foi corretamente recuperado
            if (email == null) {
                transaction.rollback();
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            JsonReader reader = Json.createReader(new StringReader(jsonString));
            JsonObject jsonObject = reader.readObject();
            reader.close();

            String name = jsonObject.getString("name");
            String tipo = jsonObject.getString("tipo");

            int cond = gerirCategoria.createCategoria(session, name, tipo, email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria criada com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -1){
                // categoria com esse nome ja existe
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria ja existe com esse nome!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -3){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Email nao registado!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else{
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (Exception e){
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



    //GET todas as categorias de um user
    @GET
    @Path("/list")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response listCategorias(@HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            JsonObject categorias = gerirCategoria.getCategorias(session, email);

            if (categorias.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nenhuma categoria encontrada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(categorias.toString(), MediaType.APPLICATION_JSON).build();

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

    //GET de uma categoria apenas de um user
    @GET
    @Path("/get/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCategoria(@PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;
        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();
            JsonObject categoria = gerirCategoria.getJsonCategoriaById(session, id,email);

            if (categoria.isEmpty()) {
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria não encontrada!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.NOT_FOUND)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            }
            transaction.commit();
            return Response.ok(categoria.toString(), MediaType.APPLICATION_JSON).build();
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
    public Response SetCategoriaUser(String jsonString, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;
        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            if (email == null) {
                transaction.rollback();
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            JsonReader reader = Json.createReader(new StringReader(jsonString));
            JsonObject jsonObject = reader.readObject();
            reader.close();

            int id = jsonObject.getInt("id");
            String name = jsonObject.getString("name");

            int cond = gerirCategoria.editCategoria(session, id, name, email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria editada com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -1){
                // categoria com esse nome ja existe
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria ja existe com esse nome!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -3){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao existe ou nao pertence a esse user!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else{
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        } catch (NumberFormatException e) {
            JsonObject jsonResponse = Json.createObjectBuilder()
                    .add("message", "Formato invalido de Id!")
                    .build();
            if( transaction != null)
                transaction.rollback();
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(jsonResponse.toString())
                    .type(MediaType.APPLICATION_JSON)
                    .build();
        } catch (Exception e){
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
    @Path("/{id}")
    @Secured
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteCategoria(@PathParam("id") int id, @HeaderParam(HttpHeaders.AUTHORIZATION) String authorizationHeader) throws PersistentException {
        String token = authorizationHeader.substring("Bearer ".length()).trim();
        String email = JWTUtil.getEmailFromToken(token);

        PersistentSession session = null;
        PersistentTransaction transaction = null;
        try{
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            if (email == null) {
                transaction.rollback();
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }

            int cond = gerirCategoria.deleteCategoria(session, id, email);

            if(cond == 0){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria eliminada com sucesso!")
                        .build();
                transaction.commit();
                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -3){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Categoria nao existe ou nao pertence a esse user!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else if(cond == -4){
                JsonObject jsonResponse = Json.createObjectBuilder()
                        .add("message", "Nao pode eliminar categorias que já estejam associadas a Transações!")
                        .build();
                transaction.rollback();
                return Response.status(Response.Status.BAD_REQUEST)
                        .entity(jsonResponse.toString())
                        .type(MediaType.APPLICATION_JSON)
                        .build();
            } else{
                transaction.rollback();
                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
            }

        }catch (Exception e){
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
