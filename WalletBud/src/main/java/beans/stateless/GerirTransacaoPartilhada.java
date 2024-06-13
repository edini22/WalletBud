package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import jakarta.json.*;

import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    public JsonObject getMovimentos(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserId(user.getId_user());

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Map<String, Object> transacao : transacoes) {
                if(transacao.get("Discriminator").equals("Unica")){
                    Unica unica = UnicaDAO.getUnicaByORMID((int) transacao.get("Id"));
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", unica.getId_transacao())
                            .add("name", unica.getName())
                            .add("value", unica.getShareValue())
                            .add("date", unica.getDate().toString())
                            .add("descricao", unica.getDescrição())
                            .add("categoria", unica.getCategoriaId_categoria().getName())
                            .add("tipo", unica.getTipo())
                            .add("local", unica.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);
                } else{
                    TransacaoFixa tf = TransacaoFixaDAO.getTransacaoFixaByORMID((int) transacao.get("Id"));
                    Fixa fixa = tf.getTransacaofixa_ID();
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", fixa.getId_transacao())
                            .add("name", fixa.getName())
                            .add("value", tf.getPayvalue())
                            .add("date", tf.getDataAtual().toString())
                            .add("datePagamento",tf.getDataPagamento().toString())
                            .add("descricao", fixa.getDescrição())
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("repeticao", fixa.getRepeticao())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);

                }
            }
            t.commit();
            return Json.createObjectBuilder()
                    .add("movimentos", arrayBuilder)
                    .build();

        } catch (Exception e) {
            t.rollback();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public JsonObject getMovimentosDays(String email, int days) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            List<Map<String, Object>> transacoes = TransacaoDAO.queryTransacoesByUserIdandDays(user.getId_user(), days);

            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
            for(Map<String, Object> transacao : transacoes) {
                if(transacao.get("Discriminator").equals("Unica")){
                    Unica unica = UnicaDAO.getUnicaByORMID((int) transacao.get("Id"));
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", unica.getId_transacao())
                            .add("name", unica.getName())
                            .add("value", unica.getShareValue())
                            .add("date", unica.getDate().toString())
                            .add("descricao", unica.getDescrição())
                            .add("categoria", unica.getCategoriaId_categoria().getName())
                            .add("tipo", unica.getTipo())
                            .add("local", unica.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);
                } else{
                    TransacaoFixa tf = TransacaoFixaDAO.getTransacaoFixaByORMID((int) transacao.get("Id"));
                    Fixa fixa = tf.getTransacaofixa_ID();
                    JsonObject unicaJson = Json.createObjectBuilder()
                            .add("id", fixa.getId_transacao())
                            .add("name", fixa.getName())
                            .add("value", tf.getPayvalue())
                            .add("date", tf.getDataAtual().toString())
                            .add("datePagamento",tf.getDataPagamento().toString())
                            .add("descricao", fixa.getDescrição())
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("repeticao", fixa.getRepeticao())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .build();
                    arrayBuilder.add(unicaJson);

                }
            }
            t.commit();
            return Json.createObjectBuilder()
                    .add("movimentos", arrayBuilder)
                    .build();

        } catch (Exception e) {
            t.rollback();
            return Json.createObjectBuilder()
                    .build();
        }

    }

}
