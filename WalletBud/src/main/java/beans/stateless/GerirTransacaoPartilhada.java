package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.*;

@Stateless
public class GerirTransacaoPartilhada {

    @EJB
    private GerirUnica gerirUnica;

    @EJB
    private GerirFixa gerirFixa;

    @EJB
    private GerirUtilizador gerirUtilizador;

    public int shareTransaction(String transacao, int IdTransacao, String email, JsonArray usersArray, Unica unica) {

        try {

            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                System.out.println("User Email: " + userEmail);
            }
            int cond;
            if(transacao.equals("fixa")){
                cond = -1;//gerirFixa.shareFixa(IdTransacao, usersArray, email);
            } else if(transacao.equals("unica")){
                cond = gerirUnica.shareUnica(IdTransacao, usersArray, email, unica);
                System.out.println("Cond: " + cond);
            } else{
//                JsonObject jsonResponse = Json.createObjectBuilder()
//                        .add("message", "Tipo de Transacao nao existe!")
//                        .build();
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity(jsonResponse.toString())
//                        .type(MediaType.APPLICATION_JSON)
//                        .build();
                return -1;
            }

            if(cond == 0){
//                JsonObject jsonResponse = Json.createObjectBuilder()
//                        .add("message", "Partilhado com sucesso!")
//                        .build();
//                return Response.status(Response.Status.CREATED).entity(jsonResponse.toString())
//                        .type(MediaType.APPLICATION_JSON)
//                        .build();
                System.out.println("herreee");
                return 0;
            } else if(cond == -1){
//                JsonObject jsonResponse = Json.createObjectBuilder()
//                        .add("message", "Algo de errado nao esta certo!")
//                        .build();
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity(jsonResponse.toString())
//                        .type(MediaType.APPLICATION_JSON)
//                        .build();
                return -2;
            } else if(cond == -2){
//                JsonObject jsonResponse = Json.createObjectBuilder()
//                        .add("message", "Ja partilhou com algum dos users!")
//                        .build();
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity(jsonResponse.toString())
//                        .type(MediaType.APPLICATION_JSON)
//                        .build();
                return -3;
            } else if(cond == -3){
//                JsonObject jsonResponse = Json.createObjectBuilder()
//                        .add("message", "Email nao registado!")
//                        .build();
//                return Response.status(Response.Status.BAD_REQUEST)
//                        .entity(jsonResponse.toString())
//                        .type(MediaType.APPLICATION_JSON)
//                        .build();
                return -4;
            } else{
//                return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
                return -5;
            }

        } catch (NumberFormatException e) {
//            JsonObject jsonResponse = Json.createObjectBuilder()
//                    .add("message", "Formato invalido do IdTransacao!")
//                    .build();
//            return Response.status(Response.Status.BAD_REQUEST)
//                    .entity(jsonResponse.toString())
//                    .type(MediaType.APPLICATION_JSON)
//                    .build();
            return -6;
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return -7;
        }

    }

}
