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
                return -1;
            }

            if(cond == 0){
                return 0;
            } else if(cond == -1){
                return -2;
            } else if(cond == -2){
                return -3;
            } else if(cond == -3){
                return -4;
            } else{
                return -5;
            }

        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
            return -7;
        }

    }

}
