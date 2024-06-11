package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonValue;

import wb.walletbud.*;

@Stateless
public class GerirTransacaoPartilhada {

    @EJB
    private GerirUnica gerirUnica;

    @EJB
    private GerirFixa gerirFixa;

    @EJB
    private GerirUtilizador gerirUtilizador;

    public int shareTransaction(String transacao, String email, JsonArray usersArray, Unica unica, Fixa fixa) {

        try {

            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                System.out.println("User Email: " + userEmail);
            }
            int cond;
            if (transacao.equals("fixa")) {
                cond = gerirFixa.shareFixa(usersArray, email, fixa);
            } else if (transacao.equals("unica")) {
                cond = gerirUnica.shareUnica(usersArray, email, unica);
                System.out.println("Cond: " + cond);
            } else {
                return -1;
            }
            return cond;

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return -7;
        }

    }

}
