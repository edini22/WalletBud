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

import java.util.Iterator;

@Stateless
public class GerirFixa {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @EJB
    private GerirCategoria gerirCategoria;

    @EJB
    private GerirTransacaoPartilhada gerirTransacaoPartilhada;

    public int createFixa(String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, int repeticao, String email, JsonArray usersArray) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) return -3;

            Categoria cat = gerirCategoria.getCategoriaById(categoria, email);
            if (cat == null) return -4;

            Fixa fixa = FixaDAO.createFixa();
            fixa.setName(name);
            fixa.setValue(value);
            fixa.setDescrição(descricao);
            fixa.setLocal(local);
            fixa.setTipo(tipo);
            fixa.setCategoriaId_categoria(cat);
            fixa.setDate(time);
            fixa.setOwner_id(user);
            fixa.setRepeticao(repeticao);

            if (usersArray.isEmpty()) {
                fixa.setStatus(true);
            } else {
                int status = gerirTransacaoPartilhada.shareTransaction("fixa", email, usersArray, null, fixa);
                if (status != 0) {
                    t.rollback();
                    System.out.println("status: " + status);
                    return status;
                }
            }

            FixaDAO.save(fixa);


            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -2;
        }

        return 0;
    }

    public int editFixa(int id, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, int repeticao, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) return -3;
            Categoria cat = null;
            if (categoria != -1) {
                cat = gerirCategoria.getCategoriaById(categoria, email);
                if (cat == null) return -4;
            }

            String condition = "Id_transacao = '" + id + "' AND UserId_user = '" + user.getId_user() + "'";
            Fixa[] fixas = FixaDAO.listFixaByQuery(condition, null);

            if (fixas.length == 0) {
                t.rollback();
                return -1;
            }

            Fixa fixa = fixas[0];

            if (!fixa.getTipo().equals(tipo)) {
                t.rollback();
                return -1;
            }

            if (name != null) fixa.setName(name);
            if (value != -1) {
                float aSvalue = fixa.getShareValue();
                float nSvalue = (value * aSvalue) / fixa.getValue();

                //iterar pelos ‘users’ todos partilhados a alterar
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

                for (TransacaoPartilhada transacao : tp) {
                    transacao.setConfirma(0);
                    TransacaoPartilhadaDAO.save(transacao);
                }

                //TODO: gerar novas notificacoes com as novas informacoes
                // destinatarios : useres que estao na lista tps(este para aceitarem ou nao) e o unica.owner(informa que foi alterado com sucesso)
                // antigo valor que lhe ficava (aSValue)
                // novo valor por quanto vai ficar (nSValue)

                fixa.setStatus(false);
                fixa.setShareValue(nSvalue);

                fixa.setValue(value);
            }
            if (descricao != null) fixa.setDescrição(descricao);
            if (local != null) fixa.setLocal(local);
            if (categoria != -1) fixa.setCategoriaId_categoria(cat);
            if (repeticao != -1) fixa.setRepeticao(repeticao);
            if (time != null) fixa.setDate(time);


            FixaDAO.save(fixa);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -2;
        }
        return 0;
    }

    public JsonObject getFixas(String email, String tipo) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "' AND Tipo = '" + tipo + "'";

            Fixa[] fixas = FixaDAO.listFixaByQuery(condition, null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Fixa f = FixaDAO.getFixaByORMID(transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if (f != null && f.getTipo().equals(tipo)) {
                        transacoes_id.add(f);
                    }
                } catch (Exception e) {
                    //nao era unica
                }
            }


            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Fixa fixa : transacoes_id) {
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

                User Owner = fixa.getOwner_id();
                JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
                JsonObject userJs = Json.createObjectBuilder()
                        .add("id", Owner.getId_user())
                        .add("name", Owner.getName())
                        .add("email", Owner.getEmail())
                        .add("confirma", 1)
                        .build();
                userArrayBuilder.add(userJs);
                for (TransacaoPartilhada tpPartilhada : tp) {
                    User u = tpPartilhada.getUserId_user();
                    JsonObject userJson = Json.createObjectBuilder()
                            .add("id", u.getId_user())
                            .add("name", u.getName())
                            .add("email", u.getEmail())
                            .add("cofirma", tpPartilhada.getConfirma())
                            .build();
                    userArrayBuilder.add(userJson);
                }
                JsonArray userArray = userArrayBuilder.build();

                JsonObject unicaJson = Json.createObjectBuilder()
                        .add("id", fixa.getId_transacao())
                        .add("name", fixa.getName())
                        .add("value", fixa.getValue())
                        .add("shareValue", fixa.getShareValue())
                        .add("date", fixa.getDate().toString())
                        .add("descricao", fixa.getDescrição())
                        .add("categoria", fixa.getCategoriaId_categoria().getName())
                        .add("status", fixa.getStatus())
                        .add("repeticao", fixa.getRepeticao())
                        .add("tipo", fixa.getTipo())
                        .add("local", fixa.getLocal())
                        .add("status", fixa.getStatus())
                        .add("users", userArray)
                        .build();
                arrayBuilder.add(unicaJson);
            }

            return Json.createObjectBuilder()
                    .add(tipo + "s", arrayBuilder.build())
                    .build();

        } catch (Exception e) {
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
            Fixa[] fixas = FixaDAO.listFixaByQuery(condition, null);


            if (fixas.length == 0) {
                return Json.createObjectBuilder()
                        .build();
            }

            Fixa fixa = fixas[0];

            condition = "TransacaoId_transacao = " + id;
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

            if (!(fixa.getOwner_id() == user || checkTransationAcess(user, tp))) {
                return Json.createObjectBuilder()
                        .build();
            }
            User Owner = fixa.getOwner_id();

            JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
            JsonObject userJs = Json.createObjectBuilder()
                    .add("id", Owner.getId_user())
                    .add("name", Owner.getName())
                    .add("email", Owner.getEmail())
                    .add("cofirma", 1)
                    .build();
            userArrayBuilder.add(userJs);
            for (TransacaoPartilhada tpPartilhada : tp) {
                User u = tpPartilhada.getUserId_user();
                JsonObject userJson = Json.createObjectBuilder()
                        .add("id", u.getId_user())
                        .add("name", u.getName())
                        .add("email", u.getEmail())
                        .add("cofirma", tpPartilhada.getConfirma())
                        .build();
                userArrayBuilder.add(userJson);
            }
            JsonArray userArray = userArrayBuilder.build();


            return Json.createObjectBuilder()
                    .add("id", fixa.getId_transacao())
                    .add("name", fixa.getName())
                    .add("value", fixa.getValue())
                    .add("shareValue", fixa.getShareValue())
                    .add("date", fixa.getDate().toString())
                    .add("descricao", fixa.getDescrição())
                    .add("categoria", fixa.getCategoriaId_categoria().getName())
                    .add("status", fixa.getStatus())
                    .add("repeticao", fixa.getRepeticao())
                    .add("tipo", fixa.getTipo())
                    .add("local", fixa.getLocal())
                    .add("status", fixa.getStatus())
                    .add("users", userArray)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }

    }

    private boolean checkTransationAcess(User user, TransacaoPartilhada[] tp) {
        //return se corresponder a algum deles
        boolean check = false;
        for (TransacaoPartilhada tpPartilhada : tp) {
            if (tpPartilhada.getUserId_user() == user) {
                check = true;
                break;
            }
        }
        return check;

    }

    public int shareFixa(JsonArray usersArray, String email, Fixa fixa) {
        try {

            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return -3;
            }

            if (fixa == null || fixa.getOwner_id() != user) {
                return -1;
            }

            int nUsers = 0;
            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                User us = gerirUtilizador.getUserByEmail(userEmail);
                if (us == null) {
                    continue;
                }
                nUsers++;
                TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                tp.setUserId_user(us);
                tp.setUsertransacaoId(fixa);
                TransacaoPartilhadaDAO.save(tp);

            }

            float nSValue = fixa.getValue() / (nUsers + 1);
            fixa.setShareValue(nSValue);
            FixaDAO.save(fixa);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int editUsersFixa(String email, int id_fixa, int option, String email_shared) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return -3;
            }

            Fixa fixa = FixaDAO.getFixaByORMID(id_fixa);
            if (fixa == null) {
                return -1;
            }

            if (fixa.getOwner_id() != user) {
                return -2;
            }

            String condition = "TransacaoId_transacao = " + fixa.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

            if (tps.length == 0 && option == -1) {
                return -4;
            }

            boolean cond = false;

            for (TransacaoPartilhada tp : tps) {

                if (tp.getUserId_user().getEmail().equals(email_shared)) {
                    cond = true;
                    if (option == -1) {
                        TransacaoPartilhadaDAO.deleteAndDissociate(tp);
                        //TODO: apagar a notificacao relacionada com este user e esta transacao ou dar update para que este rejeitou a transacao
                    }
                }
            }
            if ((cond && option == -1) || (!cond && option == 1)) {

                if (option == 1) {
                    //add user
                    User user_shared = gerirUtilizador.getUserByEmail(email_shared);
                    TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                    tp.setUserId_user(user_shared);
                    tp.setUsertransacaoId(fixa);
                    TransacaoPartilhadaDAO.save(tp);
                }

                float aSValue = fixa.getShareValue();
                float value = fixa.getValue();

                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
                int nUsers = transacoesPart.length + 1;

                float nSValue = value / nUsers;

                if (fixa.getStatus()) {
                    for (TransacaoPartilhada tpu : transacoesPart) {
                        tpu.setConfirma(0);
                        TransacaoPartilhadaDAO.save(tpu);
                    }
                    fixa.setStatus(transacoesPart.length == 0);
                }
                fixa.setShareValue(nSValue);
                FixaDAO.save(fixa);

                //TODO: gerar novas notificacoes com as novas informacoes e apaga as notificacoes antigas referidas a esta transacao
                // destinatarios : users que estao na lista tps(este para aceitarem ou nao) e o unica.owner(neste so avisa que um user rejeitou)
                // quantos users vai ter agora
                // antigo valor que lhe ficava (aSValue)
                // novo valor por quanto vai ficar (nSValue)

            } else {
                return -4;
            }

            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -1;
        }

        return 0;
    }

    public int handleFixa(String email, int id_fixa, int option) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return -3;
            }

            Fixa fixa = FixaDAO.getFixaByORMID(id_fixa);
            if (fixa == null || fixa.getStatus()) {
                return -1;
            }

            if (fixa.getOwner_id() == user) {
                return -2;
            }

            String condition = "TransacaoId_transacao = " + fixa.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

            if (tps.length == 0) {
                return -5;
            }

            condition = "TransacaoId_transacao = " + fixa.getId_transacao() + " AND UserId_user = " + user.getId_user();
            TransacaoPartilhada[] tpcheck = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
            if(tpcheck.length == 0){
                return -4;
            }

            boolean ready_to_confirm = true;
            boolean remove = false;
            ArrayList<User> users = new ArrayList<>();

            for (TransacaoPartilhada tp : tps) {

                if (tp.getUserId_user() == user) {

                    if (option == -1) {
                        if (tp.getConfirma() == 1) {
                            return -2;
                        }
                        remove = true;
                        TransacaoPartilhadaDAO.deleteAndDissociate(tp);
                    } else if (option == 1) {
                        users.add(tp.getUserId_user());
                        tp.setConfirma(1);
                        TransacaoPartilhadaDAO.save(tp);
                    }
                } else if (tp.getConfirma() == 0) {
                    ready_to_confirm = false;
                } else {
                    users.add(tp.getUserId_user());
                }
            }
            if (option == -1 && !remove) {
                return -4;
            } else if (remove) {
                //dados novos para a notificacao
                float aSValue = fixa.getShareValue();
                float value = fixa.getValue();

                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
                int nUsers = transacoesPart.length + 1;

                float nSValue = value / nUsers;
                fixa.setShareValue(nSValue);
                fixa.setStatus(false);
                FixaDAO.save(fixa);

                System.out.println("nUsers = " + nUsers);
                System.out.println("transacoesPart = " + transacoesPart.length);
                for (TransacaoPartilhada tpu : transacoesPart) {
                    tpu.setConfirma(0);
                    TransacaoPartilhadaDAO.save(tpu);
                }
                //TODO: gerar novas notificacoes com as novas informacoes e apaga as notificacoes antigas referidas a esta transacao
                // destinatarios : useres que estao na lista tps(este para aceitarem ou nao) e o unica.owner(neste so avisa que um user rejeitou)
                // quantos users desistiram
                // antigo valor que lhe ficava (aSValue)
                // novo valor por quanto vai ficar (nSValue)


            }
            if (ready_to_confirm && (!remove || users.isEmpty())) {
                System.out.println("confirmaUnica");
                fixa.setStatus(true);
                int nUsers = users.size() + 1;
                float nSValue = fixa.getValue() / nUsers;
                fixa.setShareValue(nSValue);
                FixaDAO.save(fixa);
                // chamar função para confirmar e atualizar saldos
                // confirmUnica(fixa);
            }

            t.commit();
        } catch (Exception e) {
            System.out.println("ardeu ->" + e);
            t.rollback();
            return -1;
        }

        return 0;
    }

    public int payFixa(String email, int id_fixa, Timestamp dateNow, Timestamp date) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return -3;
            }

            Fixa fixa = FixaDAO.getFixaByORMID(id_fixa);
            if (fixa == null ) {
                return -1;
            }

            if (fixa.getOwner_id() != user) {
                return -2;
            }

            if (!fixa.getStatus()) {
                return -4;
            }

            String condition = "TransacaoId_transacao = " + fixa.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

            TransacaoFixa tf = TransacaoFixaDAO.createTransacaoFixa();
            tf.setTransacaofixa_ID(fixa);
            tf.setDataAtual(dateNow);
            tf.setDataPagamento(date);
            tf.setPayvalue(fixa.getShareValue());

            TransacaoFixaDAO.save(tf);

            //owner
            user.transacaoFixa.add(tf);
            //atualizar saldos
            if (fixa.getTipo().equals("receita")) {
                user.setSaldo(user.getSaldo() + fixa.getShareValue());
            } else {
                user.setSaldo(user.getSaldo() - fixa.getShareValue());
            }
            UserDAO.save(user);
            for (TransacaoPartilhada tp : tps) {
                User u = tp.getUserId_user();
                u.transacaoFixa.add(tf);
                if (fixa.getTipo().equals("receita")) {
                    u.setSaldo(u.getSaldo() + fixa.getShareValue());
                } else {
                    u.setSaldo(u.getSaldo() - fixa.getShareValue());
                }
                UserDAO.save(u);

                //TODO: notificar todos os utilizadores (u) que uma despes/receita fixa foi paga
                // NOTA: não esquecer de enviar notificação para toda os utilizadores e o proprio
                // owner 3 dias antes
            }

            t.commit();
        } catch (Exception e){
            t.rollback();
        }
        return 0;
    }

    public JsonObject getJsonPaymentFixa(String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();

            for (Iterator it = user.transacaoFixa.getIterator(); it.hasNext();) {
                TransacaoFixa tf = (TransacaoFixa) it.next();

                JsonObject userJson = Json.createObjectBuilder()
                        .add("id", tf.getTransacaofixa_ID().getId_transacao())
                        .add("name", tf.getTransacaofixa_ID().getName())
                        .add("value", tf.getPayvalue())
                        .add("descricao", tf.getTransacaofixa_ID().getDescrição())
                        .add("descricao", tf.getTransacaofixa_ID().getDescrição())
                        .add("categoria", tf.getTransacaofixa_ID().getCategoriaId_categoria().getName())
                        .add("repeticao", tf.getTransacaofixa_ID().getRepeticao())
                        .add("tipo", tf.getTransacaofixa_ID().getTipo())
                        .add("local", tf.getTransacaofixa_ID().getLocal())
                        .build();
                userArrayBuilder.add(userJson);

            }
            JsonArray response = userArrayBuilder.build();


            return Json.createObjectBuilder()
                    .add("transacoes", response)
                    .build();

        } catch (Exception e) {
            return Json.createObjectBuilder()
                    .build();
        }

    }

}
