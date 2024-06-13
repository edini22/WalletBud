package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;

import jakarta.json.*;

import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.*;

import java.sql.Timestamp;
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

    public JsonObject getTimeline(String email,int ano, int mes) throws PersistentException {//Em processo de construcao
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            //TODO: ainda nao fiz nada, mas o objetivo e apresentar as fixas e unicas ao qual o user pagou/tem a pagar e as unicas que pagou (status = true)

            String condition = "UserId_user = " + user.getId_user();

            Fixa[] fixas = FixaDAO.listFixaByQuery(condition, null);

            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Fixa f = FixaDAO.getFixaByORMID(transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if (f != null) {
                        transacoes_id.add(f);
                    }
                } catch (Exception e) {
                    //nao era fixa
                }
            }

            String orederby = "";
            Timestamp curTime = new Timestamp(System.currentTimeMillis());

            JsonArrayBuilder lateArrayBuilder = Json.createArrayBuilder();

            for (Fixa fixa : transacoes_id) {
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                orederby = "DataPagamento DESC";

                TransacaoFixa[] tfs = TransacaoFixaDAO.listTransacaoFixaByQuery(condition, orederby);
                Timestamp time ;
                if (tfs.length == 0) {
                    System.out.println(fixa.getDate());
                    time = new Timestamp(fixa.getDate().getTime());
                } else {
                    System.out.println(tfs[0].getDataPagamento());
                    time = new Timestamp(tfs[0].getDataPagamento().getTime());
                    if (fixa.getRepeticao() == 1) { //diariamente
                        time.setDate(time.getDate() + 1);
                    } else if (fixa.getRepeticao() == 2) { // semanalmente
                        time.setDate(time.getDate() + 7);
                    } else if (fixa.getRepeticao() == 3) { //Mensalmente
                        time.setMonth(time.getMonth() + 1);
                    } else if (fixa.getRepeticao() == 4) { //Anualmente
                        time.setYear(time.getYear() + 1);
                    }
                }

                boolean first = true;
                while(curTime.after(time)){
                    System.out.println();
                    JsonObject lateJson = Json.createObjectBuilder()
                            .add("id",fixa.getId_transacao())
                            .add("name", fixa.getName())
                            .add("value", fixa.getShareValue())
                            .add("descricao", fixa.getDescrição())
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("repeticao", fixa.getRepeticao())
                            .add("tipo", fixa.getTipo())
                            .add("local", fixa.getLocal())
                            .add("data_pagamento", time.toString())
                            .add("status",first)
                            .build();

                    lateArrayBuilder.add(lateJson);

                    first = false;
                    if (fixa.getRepeticao() == 1) { //diariamente
                        time.setDate(time.getDate() + 1);
                    } else if (fixa.getRepeticao() == 2) { // semanalmente
                        time.setDate(time.getDate() + 7);
                    } else if (fixa.getRepeticao() == 3) { //Mensalmente
                        time.setMonth(time.getMonth() + 1);
                    } else if (fixa.getRepeticao() == 4) { //Anualmente
                        time.setYear(time.getYear() + 1);
                    }
                }
                System.out.println();

            }

            t.commit();
            return Json.createObjectBuilder()
                    .add("leite", lateArrayBuilder)
                    .build();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }
    }

}
