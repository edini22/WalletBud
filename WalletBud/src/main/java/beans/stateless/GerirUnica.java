package beans.stateless;


import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

@Stateless
public class GerirUnica {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @EJB
    private GerirCategoria gerirCategoria;

    @EJB
    private GerirTransacaoPartilhada gerirTransacaoPartilhada;

    public int createUnica(String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, String email, JsonArray usersArray) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if(user == null) return -3;

            Categoria cat = gerirCategoria.getCategoriaById(categoria, email);
            if(cat == null) return -4;

            Unica unica = UnicaDAO.createUnica();
            unica.setName(name);
            unica.setValue(value);
            unica.setShareValue(value);
            unica.setDescrição(descricao);
            unica.setLocal(local);
            unica.setTipo(tipo);
            unica.setCategoriaId_categoria(cat);
            unica.setDate(time);
            unica.setOwner_id(user);

            // como sabemos se o gajo vai ou não partilhar a transacao status = ?
            if (usersArray.isEmpty()) {
                unica.setStatus(true);

                float saldo = user.getSaldo();
                if(tipo.equals("receita")){
                    saldo += value;
                }else {
                    saldo -= value;
                }
                user.setSaldo(saldo);
                UserDAO.save(user);

            } else {
                int status = gerirTransacaoPartilhada.shareTransaction("unica", unica.getId_transacao(), email, usersArray, unica);
                if (status != 0) {
                    t.rollback();
                    return status;
                }
            }

            UnicaDAO.save(unica);

            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return -2;
        }

        return 0;
    }

    public int editUnica(int id, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time,String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if(user == null) return -3;
            Categoria cat = null;
            if(categoria != -1) {
                cat = gerirCategoria.getCategoriaById(categoria, email);
                if(cat == null) return -4;
            }

            String condition = "Id_transacao = '" + id + "' AND UserId_user = '" + user.getId_user() + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition, null);

            if (unicas.length == 0) {
                t.rollback();
                return -1;
            }

            Unica unica = unicas[0];

            if(!unica.getTipo().equals(tipo)){
                t.rollback();
                return -1;
            }

            if (name != null) unica.setName(name);
            if (value != -1){
                if (unica.getStatus()) {
                    float aSvalue = unica.getShareValue();
                    float nSvalue = (value * aSvalue) / unica.getValue();
                    float diff = nSvalue - aSvalue ;

                    if(unica.getTipo().equals("receita")){
                        user.setSaldo(user.getSaldo() + diff);
                    } else {
                        user.setSaldo(user.getSaldo() - diff);
                    }
                    UserDAO.save(user);

                    //iterar pelos user todos partilhados a alterar
                    condition = "TransacaoId_transacao = " + unica.getId_transacao();
                    TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                    for(TransacaoPartilhada transacao : tp){
                        User u = transacao.getUserId_user();
                        if(unica.getTipo().equals("receita")){
                            u.setSaldo(u.getSaldo() + diff);
                        } else {
                            u.setSaldo(u.getSaldo() - diff);
                        }
                        UserDAO.save(u);
                    }

                    unica.setShareValue(nSvalue);

                }

                unica.setValue(value);
            }
            if (descricao != null) unica.setDescrição(descricao);
            if (local != null) unica.setLocal(local);
            if (categoria != -1) unica.setCategoriaId_categoria(cat);
            if (time != null) unica.setDate(time);

            UnicaDAO.save(unica);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return -2;
        }
        return 0;
    }

    public JsonObject getUnicas(String email, String tipo) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if(user == null){
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "' AND Tipo = '" + tipo + "'";

            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition,null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);
            ArrayList<Unica> transacoes_id = new ArrayList<>(Arrays.asList(unicas));

            for(TransacaoPartilhada transacaoPartilhada : transacoes_partilhada){
                try{
                    Unica u = UnicaDAO.getUnicaByORMID(transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if(u != null && u.getTipo().equals(tipo)){
                        transacoes_id.add(u);
                    }
                } catch (Exception e){
                    //nao era unica
                }
            }


            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Unica unica : transacoes_id) {
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                User Owner = unica.getOwner_id();
                JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
                JsonObject userJs = Json.createObjectBuilder()
                        .add("id", Owner.getId_user())
                        .add("name", Owner.getName())
                        .add("email", Owner.getEmail())
                        .build();
                userArrayBuilder.add(userJs);
                for (TransacaoPartilhada tpPartilhada : tp) {
                    User u = tpPartilhada.getUserId_user();
                    JsonObject userJson = Json.createObjectBuilder()
                            .add("id", u.getId_user())
                            .add("name", u.getName())
                            .add("email", u.getEmail())
                            .build();
                    userArrayBuilder.add(userJson);
                }
                JsonArray userArray = userArrayBuilder.build();

                JsonObject unicaJson = Json.createObjectBuilder()
                        .add("id", unica.getId_transacao())
                        .add("name", unica.getName())
                        .add("value", unica.getValue())
                        .add("shareValue", unica.getShareValue())
                        .add("date", unica.getDate().toString())
                        .add("descricao", unica.getDescrição())
                        .add("categoria",unica.getCategoriaId_categoria().getName())
                        .add("tipo", unica.getTipo())
                        .add("local", unica.getLocal())
                        .add("status", unica.getStatus())
                        .add("users", userArray)
                        .build();
                arrayBuilder.add(unicaJson);
            }

            return Json.createObjectBuilder()
                    .add(tipo + "s", arrayBuilder.build())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    public JsonObject getJsonUnicaById(int id, String email, String tipo) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "Id_transacao = " + id + " AND Tipo = '" + tipo + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition, null); // ate aqui qualuer um pode requisitar esta transacao


            if (unicas.length == 0) {
                return Json.createObjectBuilder()
                        .build();
            }

            Unica unica = unicas[0];

            condition = "TransacaoId_transacao = " + id;
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);
            //verificar se o owner o se com quem esta transacao esta partilhada corresponde ao user que esta a pedir, senao se verificar return vazio
            if(!(unica.getOwner_id() == user || checkTransationAcess(user, tp)) ){
                return Json.createObjectBuilder()
                        .build();
            }
            User Owner = unica.getOwner_id();
            //ver com quem esta partilhada a transacao
            // Construir o JsonArray para a lista de usuários
            JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
            JsonObject userJs = Json.createObjectBuilder()
                    .add("id", Owner.getId_user())
                    .add("name", Owner.getName())
                    .add("email", Owner.getEmail())
                    .build();
            userArrayBuilder.add(userJs);
            for (TransacaoPartilhada tpPartilhada : tp) {
                User u = tpPartilhada.getUserId_user();
                JsonObject userJson = Json.createObjectBuilder()
                        .add("id", u.getId_user())
                        .add("name", u.getName())
                        .add("email", u.getEmail())
                        .add("confirma", tpPartilhada.getConfirma())
                        .build();
                userArrayBuilder.add(userJson);
            }
            JsonArray userArray = userArrayBuilder.build();

            JsonObject unicaJson = Json.createObjectBuilder()
                    .add("id", unica.getId_transacao())
                    .add("name", unica.getName())
                    .add("value", unica.getValue())
                    .add("shareValue", unica.getShareValue())
                    .add("date", unica.getDate().toString())
                    .add("descricao", unica.getDescrição())
                    .add("categoria",unica.getCategoriaId_categoria().getName())
                    .add("tipo", unica.getTipo())
                    .add("local", unica.getLocal())
                    .add("status", unica.getStatus())
                    .add("users", userArray)
                    .build();

            return unicaJson;

        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    private boolean checkTransationAcess( User user , TransacaoPartilhada[] tp ){
        //return se corresponder a algum deles
        boolean check = false;
        for(TransacaoPartilhada tpPartilhada : tp){
            if( tpPartilhada.getUserId_user() == user){
                check = true;
                break;
            }
        }
        return check;

    }


    public int shareUnica(int idUnica, JsonArray usersArray, String email, Unica unica) throws PersistentException {
        try{

            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return -3;
            }
//            Unica unica = UnicaDAO.getUnicaByORMID(idUnica);

            if(unica == null || unica.getOwner_id() != user){
                return -1;
            }

            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                User us = gerirUtilizador.getUserByEmail(userEmail);
                if (us == null) {
                    continue;
                }

                //verificar se este user ja esta com acesso a transacao ou seja
                String condition = "TransacaoId_transacao = " + idUnica + " AND UserId_user = " + us.getId_user();
                TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                if(tps.length == 0 && unica.getOwner_id() != us){
                    TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                    tp.setUserId_user(us);
                    tp.setUsertransacaoId(unica);
                    TransacaoPartilhadaDAO.save(tp);
                }
                else{
                    return -2;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int handleUnica(String email, int id_unica,int option) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {return -3;}

            Unica unica = UnicaDAO.getUnicaByORMID(id_unica);
            if (unica == null || unica.getStatus()) {return -1;}

            if (unica.getOwner_id() == user) {return -2;}

            String condition = "TransacaoId_transacao = " + unica.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

            if (tps.length == 0) {return -4;}

            boolean ready_to_confirm = true;
            ArrayList<User> users = new ArrayList<>();

            for (TransacaoPartilhada tp : tps) {

                if (tp.getUserId_user() == user) {
                    if (option == -1)
                        TransacaoPartilhadaDAO.deleteAndDissociate(tp);
                    else if (option == 1) {
                        users.add(tp.getUserId_user());
                        tp.setConfirma(1);
                        TransacaoPartilhadaDAO.save(tp);
                    }
                } else if (option == -1 && tp.getConfirma() == 0){
                    ready_to_confirm = false;
                } else if (option == 1 && tp.getConfirma() == 0) {
                    ready_to_confirm = false;
                } else {
                    users.add(tp.getUserId_user());
                }
            }

            if (ready_to_confirm) {
                // chamar função para confirmar e atualizar saldos
                confirmUnica(unica, users);
            }

            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -1;
        }

        return 0;
    }

    public int confirmUnica(Unica unica, ArrayList<User> usersArray) throws PersistentException {

        User user = unica.getOwner_id();

        // atualizar saldos
//        float aSvalue = unica.getShareValue();
//        int anUsers = (int) (unica.getValue() / unica.getShareValue());
//        float nSvalue = unica.getValue() / (anUsers + usersArray.size());
//        float diff = nSvalue - aSvalue;

        float nSvalue = unica.getValue() / (usersArray.size() + 1); // + owner


        if(unica.getTipo().equals("receita")){
            user.setSaldo(user.getSaldo() + nSvalue);
        } else {
            user.setSaldo(user.getSaldo() - nSvalue);
        }
        UserDAO.save(user);

        String condition = "TransacaoId_transacao = " + unica.getId_transacao();
        TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

        for(TransacaoPartilhada transacao : tp){
            User u = transacao.getUserId_user();
//            boolean exists = false;
//            for (User userValue : usersArray) {
//                String userEmail = userValue.getEmail();
//                if(u.getEmail().equals(userEmail)){
//                    exists = true;
//                    break;
//                }
//            }
//            if(!exists){
//                if(unica.getTipo().equals("receita")){
//                    u.setSaldo(u.getSaldo() + diff);
//                } else {
//                    u.setSaldo(u.getSaldo() - diff);
//                }
//            } else {
                if(unica.getTipo().equals("receita")){
                    u.setSaldo(u.getSaldo() + nSvalue);
                } else {
                    u.setSaldo(u.getSaldo() - nSvalue);
                }
//            }

            UserDAO.save(u);
        }

        unica.setShareValue(nSvalue);
        unica.setStatus(true);
        UnicaDAO.save(unica);

        return 0;
    }

}
