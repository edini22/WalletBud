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

@Stateless
public class GerirFixa {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @EJB
    private GerirCategoria gerirCategoria;

    public int createFixa(String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, int repeticao, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if(user == null) return -3;

            Categoria cat = gerirCategoria.getCategoriaById(categoria, email);
            if(cat == null) return -4;

            Fixa fixa = FixaDAO.createFixa();
            fixa.setName(name);
            fixa.setValue(value);
            fixa.setShareValue(value);
            fixa.setDescrição(descricao);
            fixa.setLocal(local);
            fixa.setTipo(tipo);
            fixa.setCategoriaId_categoria(cat);
            fixa.setDate(time);
            fixa.setOwner_id(user);
            fixa.setRepeticao(repeticao);
            FixaDAO.save(fixa);


            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return -2;
        }

        return 0;
    }

    public int editFixa(int id, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, int repeticao, String email) throws PersistentException {
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
            Fixa[] fixas = FixaDAO.listFixaByQuery(condition, null);

            if (fixas.length == 0) {
                t.rollback();
                return -1;
            }

            Fixa fixa = fixas[0];

            if(!fixa.getTipo().equals(tipo)){
                t.rollback();
                return -1;
            }

            if (name != null) fixa.setName(name);
            if (value != -1){fixa.setValue(value);}
            if (descricao != null) fixa.setDescrição(descricao);
            if (local != null) fixa.setLocal(local);
            if (categoria != -1) fixa.setCategoriaId_categoria(cat);
            if (repeticao != -1) fixa.setRepeticao(repeticao);
            if (time != null) fixa.setDate(time);

            FixaDAO.save(fixa);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            e.printStackTrace();
            return -2;
        }
        return 0;
    }

    public JsonObject getFixas(String email, String tipo) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if(user == null){
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "' AND Tipo = '" + tipo + "'";

            Fixa[] fixas = FixaDAO.listFixaByQuery(condition,null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for(TransacaoPartilhada transacaoPartilhada : transacoes_partilhada){
                try{
                    Fixa u = FixaDAO.getFixaByORMID(transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if(u != null && u.getTipo().equals(tipo)){
                        transacoes_id.add(u);
                    }
                } catch (Exception e){
                    //nao era fixa
                }
            }


            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Fixa fixa : transacoes_id) {
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                User Owner = fixa.getOwner_id();
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

                JsonObject fixaJson = Json.createObjectBuilder()
                        .add("id", fixa.getId_transacao())
                        .add("name", fixa.getName())
                        .add("value", fixa.getValue())
                        .add("shareValue", fixa.getShareValue())
                        .add("date", fixa.getDate().toString())
                        .add("descricao", fixa.getDescrição())
                        .add("categoria",fixa.getCategoriaId_categoria().getName())
                        .add("tipo", fixa.getTipo())
                        .add("local", fixa.getLocal())
                        .add("repeticao", fixa.getRepeticao())
                        .add("users", userArray)
                        .build();
                arrayBuilder.add(fixaJson);
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

    public JsonObject getJsonFixaById(int id, String email, String tipo) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "Id_transacao = " + id + " AND Tipo = '" + tipo + "'";
            Fixa[] fixas = FixaDAO.listFixaByQuery(condition, null); // ate aqui qualuer um pode requisitar esta transacao


            if (fixas.length == 0) {
                return Json.createObjectBuilder()
                        .build();
            }

            Fixa fixa = fixas[0];

            condition = "TransacaoId_transacao = " + id;
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);
            //verificar se o owner o se com quem esta transacao esta partilhada corresponde ao user que esta a pedir, senao se verificar return vazio
            if(!(fixa.getOwner_id() == user || checkTransationAcess(user, tp)) ){
                return Json.createObjectBuilder()
                        .build();
            }
            User Owner = fixa.getOwner_id();
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
                        .build();
                userArrayBuilder.add(userJson);
            }
            JsonArray userArray = userArrayBuilder.build();


            JsonObject fixaJson = Json.createObjectBuilder()
                    .add("id", fixa.getId_transacao())
                    .add("name", fixa.getName())
                    .add("value", fixa.getValue())
                    .add("shareValue", fixa.getShareValue())
                    .add("date", fixa.getDate().toString())
                    .add("descricao", fixa.getDescrição())
                    .add("categoria",fixa.getCategoriaId_categoria().getName())
                    .add("tipo", fixa.getTipo())
                    .add("local", fixa.getLocal())
                    .add("repeticao", fixa.getRepeticao())
                    .add("users", userArray)
                    .build();

            return fixaJson;

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

    public int shareFixa(int idFixa, JsonArray usersArray, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try{

            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return -3;
            }
            Fixa fixa = FixaDAO.getFixaByORMID(idFixa);

            if(fixa == null || fixa.getOwner_id() != user){
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
                String condition = "TransacaoId_transacao = " + idFixa + " AND UserId_user = " + us.getId_user();
                TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition,null);

                if(tps.length == 0 && fixa.getOwner_id() != us){
                    TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                    tp.setUserId_user(us);
                    tp.setUsertransacaoId(fixa);
                    TransacaoPartilhadaDAO.save(tp);
                }
                else{
                    t.rollback();
                    return -2;
                }
            }

            int anUsers = (int) (fixa.getValue() / fixa.getShareValue());

            float nSvalue = fixa.getValue() / (anUsers + usersArray.size());

            fixa.setShareValue(nSvalue);
            FixaDAO.save(fixa);

            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -1;
        }
        return 0;
    }

}
