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
public class GerirUnica {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @EJB
    private GerirCategoria gerirCategoria;

    @EJB
    private GerirTransacaoPartilhada gerirTransacaoPartilhada;

    @EJB
    private GerirComentario gerirComentario;

    public int createUnica(String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, String email, JsonArray usersArray, String comentario) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) return -3;

            Categoria cat = gerirCategoria.getCategoriaById(categoria, email);
            if (cat == null) return -4;

            Unica unica = UnicaDAO.createUnica();
            unica.setName(name);
            unica.setValue(value);
            unica.setDescrição(descricao);
            unica.setLocal(local);
            unica.setTipo(tipo);
            unica.setCategoriaId_categoria(cat);
            unica.setDate(time);
            unica.setOwner_id(user);

            if (usersArray.isEmpty()) {
                unica.setStatus(true);
                unica.setShareValue(value);
                float saldo = user.getSaldo();
                if (tipo.equals("receita")) {
                    saldo += value;
                } else {
                    saldo -= value;
                }
                user.setSaldo(saldo);
                UserDAO.save(user);

            } else {
                int status = gerirTransacaoPartilhada.shareTransaction("unica", email, usersArray, unica, null);
                if (status != 0) {
                    t.rollback();
                    return status;
                }
            }

            if (!comentario.isEmpty()) {
                gerirComentario.createComentario(comentario, user, unica);
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

    public int editUnica(int id, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                t.rollback();
                return -3;
            }
            Categoria cat = null;
            if (categoria != -1) {
                cat = gerirCategoria.getCategoriaById(categoria, email);
                if (cat == null) {
                    t.rollback();
                    return -4;
                }
            }

            String condition = "Id_transacao = '" + id + "' AND UserId_user = '" + user.getId_user() + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition, null);

            if (unicas.length == 0) {
                t.rollback();
                return -1;
            }

            Unica unica = unicas[0];

            if (!unica.getTipo().equals(tipo)) {
                t.rollback();
                return -1;
            }

            if (name != null) unica.setName(name);
            if (value != -1) {
                float aSvalue = unica.getShareValue();
                float nSvalue = (value * aSvalue) / unica.getValue();

                if (unica.getStatus()) {
                    if (unica.getTipo().equals("receita")) {
                        user.setSaldo(user.getSaldo() - aSvalue);
                    } else {
                        user.setSaldo(user.getSaldo() + aSvalue);
                    }
                    UserDAO.save(user);
                    //TODO: eliminar todas as notificacoes referentes a esta transacao
                }

                //iterar pelos ‘users’ todos partilhados a alterar
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

                for (TransacaoPartilhada transacao : tp) {
                    User u = transacao.getUserId_user();
                    if (unica.getTipo().equals("receita")) {
                        u.setSaldo(u.getSaldo() - aSvalue);
                    } else {
                        u.setSaldo(u.getSaldo() + aSvalue);
                    }
                    UserDAO.save(u);
                    transacao.setConfirma(0);
                    TransacaoPartilhadaDAO.save(transacao);
                }

                //TODO: gerar novas notificacoes com as novas informacoes
                // destinatarios : useres que estao na lista tps(este para aceitarem ou nao) e o unica.owner(informa que foi alterado com sucesso)
                // antigo valor que lhe ficava (aSValue)
                // novo valor por quanto vai ficar (nSValue)

                unica.setStatus(false);
                unica.setShareValue(nSvalue);

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
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "' AND Tipo = '" + tipo + "'";

            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition, null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
            ArrayList<Unica> transacoes_id = new ArrayList<>(Arrays.asList(unicas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Unica u = UnicaDAO.getUnicaByORMID(transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if (u != null && u.getTipo().equals(tipo)) {
                        transacoes_id.add(u);
                    }
                } catch (Exception e) {
                    //nao era unica
                }
            }


            JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

            for (Unica unica : transacoes_id) {
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

                User Owner = unica.getOwner_id();
                JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
                JsonObject userJs = Json.createObjectBuilder()
                        .add("id", Owner.getId_user())
                        .add("name", Owner.getName())
                        .add("email", Owner.getEmail())
                        .add("confirma", true)
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
                        .add("id", unica.getId_transacao())
                        .add("name", unica.getName())
                        .add("value", unica.getValue())
                        .add("shareValue", unica.getShareValue())
                        .add("date", unica.getDate().toString())
                        .add("descricao", unica.getDescrição())
                        .add("categoria", unica.getCategoriaId_categoria().getName())
                        .add("status", unica.getStatus())
                        .add("tipo", unica.getTipo())
                        .add("local", unica.getLocal())
                        .add("status", unica.getStatus())
                        .add("users", userArray)
                        .build();
                arrayBuilder.add(unicaJson);
            }
            t.commit();
            return Json.createObjectBuilder()
                    .add(tipo + "s", arrayBuilder.build())
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
            return Json.createObjectBuilder()
                    .build();
        }
    }

    public JsonObject getJsonUnicaById(int id, String email, String tipo) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "Id_transacao = " + id + " AND Tipo = '" + tipo + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(condition, null); // ate aqui qualuer um pode requisitar esta transacao


            if (unicas.length == 0) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }

            Unica unica = unicas[0];

            condition = "TransacaoId_transacao = " + id;
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
            //verificar se o owner o se com quem está transacao esta partilhada corresponde ao ‘user’ que pede, senao se verificar return vazio
            if (!(unica.getOwner_id() == user || checkTransationAcess(user, tp))) {
                t.rollback();
                return Json.createObjectBuilder()
                        .build();
            }
            User Owner = unica.getOwner_id();
            //ver com quem está partilhada a transacao
            // Construir o JsonArray para a lista de utilizadores
            JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();
            JsonObject userJs = Json.createObjectBuilder()
                    .add("id", Owner.getId_user())
                    .add("name", Owner.getName())
                    .add("email", Owner.getEmail())
                    .add("confirma", true)
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
                    .add("categoria", unica.getCategoriaId_categoria().getName())
                    .add("status", unica.getStatus())
                    .add("tipo", unica.getTipo())
                    .add("local", unica.getLocal())
                    .add("status", unica.getStatus())
                    .add("users", userArray)
                    .build();
            t.commit();
            return unicaJson;

        } catch (Exception e) {
            e.printStackTrace();
            t.rollback();
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


    public int shareUnica(JsonArray usersArray, String email, Unica unica) {
        try {

            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                return -3;
            }

            if (unica == null || unica.getOwner_id() != user) {
                return -1;
            }
            int nUsers = 0;
            for (JsonValue userValue : usersArray) {
                JsonObject userObject = userValue.asJsonObject();
                String userEmail = userObject.getString("email");

                User us = gerirUtilizador.getUserByEmail(userEmail);
                if (us == null) {
                    return -5;
                }
                //verificar se o user ja esta naquela transacao
                String condition = "TransacaoId_transacao = " + unica.getId_transacao() + " AND UserId_user = " + user.getId_user();
                TransacaoPartilhada[] tpcheck = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
                if(tpcheck.length != 0){
                    return -5;
                }

                nUsers++;
                TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                tp.setUserId_user(us);
                tp.setUsertransacaoId(unica);
                TransacaoPartilhadaDAO.save(tp);

            }

            float nSValue = unica.getValue() / (nUsers + 1);
            unica.setShareValue(nSValue);
            UnicaDAO.save(unica);

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public int editUsersUnica(String email, int id_unica, int option, String email_shared) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                t.rollback();
                return -3;
            }

            Unica unica = UnicaDAO.getUnicaByORMID(id_unica);
            if (unica == null) {
                t.rollback();
                return -1;
            }

            if (unica.getOwner_id() != user) {
                t.rollback();
                return -2;
            }

            String condition = "TransacaoId_transacao = " + unica.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

            int oldUsers = tps.length + 1;

            if (tps.length == 0 && option == -1) {
                t.rollback();
                return -4;
            }

            boolean cond = false;

            for (TransacaoPartilhada tp : tps) {

                if (tp.getUserId_user().getEmail().equals(email_shared)) {
                    cond = true;
                    if (option == -1) {
                        if (unica.getStatus()) {
                            float saldo = user.getSaldo();
                            User us = tp.getUserId_user();
                            if (unica.getTipo().equals("receita")) {
                                us.setSaldo(saldo - unica.getShareValue());
                            } else {
                                us.setSaldo(saldo + unica.getShareValue());
                            }
                            UserDAO.save(us);
                        }
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
                    tp.setUsertransacaoId(unica);
                    TransacaoPartilhadaDAO.save(tp);
                }

                float aSValue = unica.getShareValue();
                float value = unica.getValue();

                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
                int nUsers = transacoesPart.length + 1;

                float nSValue = value / nUsers;

                if (unica.getStatus()) {
                    if (unica.getTipo().equals("receita")) {
                        user.setSaldo(user.getSaldo() - unica.getShareValue());
                    } else {
                        user.setSaldo(user.getSaldo() + unica.getShareValue());
                    }
                    UserDAO.save(user);
                    for (TransacaoPartilhada tpu : transacoesPart) {
                        //+/- guita
                        User us = tpu.getUserId_user();
                        if (!us.getEmail().equals(email_shared)) {
                            if (unica.getTipo().equals("receita")) {
                                us.setSaldo(us.getSaldo() - aSValue);
                            } else {
                                us.setSaldo(us.getSaldo() + aSValue);
                            }
                        }
                        tpu.setConfirma(0);
                        TransacaoPartilhadaDAO.save(tpu);
                    }
                    if (transacoesPart.length == 0) {
                        confirmUnica(unica);
                    } else {
                        unica.setStatus(false);
                    }
                }
                unica.setShareValue(nSValue);
                UnicaDAO.save(unica);

                //TODO: gerar novas notificacoes com as novas informacoes e apaga as notificacoes antigas referidas a esta transacao
                // destinatarios : users que estao na lista tps(este para aceitarem ou nao) e o unica.owner(neste so avisa que um user rejeitou)
                // quantos users vai ter agora
                // antigo valor que lhe ficava (aSValue)
                // novo valor por quanto vai ficar (nSValue)

            } else {
                t.rollback();
                return -4;
            }

            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -1;
        }

        return 0;
    }

    public int handleUnica(String email, int id_unica, int option) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                t.rollback();
                return -3;
            }

            Unica unica = UnicaDAO.getUnicaByORMID(id_unica);
            if (unica == null || unica.getStatus()) {
                t.rollback();
                return -1;
            }

            if (unica.getOwner_id() == user) {
                t.rollback();
                return -2;
            }

            String condition = "TransacaoId_transacao = " + unica.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

            int oldUsers = tps.length + 1;

            if (tps.length == 0) {
                t.rollback();
                return -5;
            }

            condition = "TransacaoId_transacao = " + unica.getId_transacao() + " AND UserId_user = " + user.getId_user();
            TransacaoPartilhada[] tpcheck = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
            if(tpcheck.length == 0){
                t.rollback();
                return -4;
            }

            boolean ready_to_confirm = true;
            boolean remove = false;
            ArrayList<User> users = new ArrayList<>();

            for (TransacaoPartilhada tp : tps) {

                if (tp.getUserId_user() == user) {

                    if (option == -1) {
                        if (tp.getConfirma() == 1) {
                            t.rollback();
                            return -2;
                        }
                        remove = true;
                        if (unica.getStatus()) {
                            User us = tp.getUserId_user();
                            float saldo = user.getSaldo();
                            if (unica.getTipo().equals("receita")) {
                                us.setSaldo(saldo - unica.getShareValue());
                            } else {
                                us.setSaldo(saldo + unica.getShareValue());
                            }
                            UserDAO.save(us);
                        }
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
                t.rollback();
                return -4;
            } else if (remove) {
                //dados novos para a notificacao
                float aSValue = unica.getShareValue();
                float value = unica.getValue();

                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);
                int nUsers = transacoesPart.length + 1;

                float nSValue = value / nUsers;
                unica.setShareValue(nSValue);
                unica.setStatus(false);
                UnicaDAO.save(unica);

                System.out.println("nUsers = " + nUsers);
                System.out.println("transacoesPart = " + transacoesPart.length);
                if (unica.getStatus()) {
                    if (unica.getTipo().equals("receita")) {
                        user.setSaldo(user.getSaldo() - unica.getShareValue());
                    } else {
                        user.setSaldo(user.getSaldo() + unica.getShareValue());
                    }
                    UserDAO.save(user);
                }
                for (TransacaoPartilhada tpu : transacoesPart) {
                    User us = tpu.getUserId_user();
                    if (unica.getStatus()) {
                        if (unica.getTipo().equals("receita")) {
                            us.setSaldo(us.getSaldo() - aSValue);
                        } else {
                            us.setSaldo(us.getSaldo() + aSValue);
                        }
                    }
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
                // chamar função para confirmar e atualizar saldos
                confirmUnica(unica);
            }

            t.commit();
        } catch (Exception e) {
            System.out.println("ardeu ->" + e);
            t.rollback();
            return -1;
        }

        return 0;
    }

    public int confirmUnica(Unica unica) throws PersistentException {

        User user = unica.getOwner_id();

        String condition = "TransacaoId_transacao = " + unica.getId_transacao();
        TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

        float nSvalue = unica.getValue() / (tp.length + 1); // + owner

        if (unica.getTipo().equals("receita")) {
            user.setSaldo(user.getSaldo() + nSvalue);
        } else {
            user.setSaldo(user.getSaldo() - nSvalue);
        }
        UserDAO.save(user);

        for (TransacaoPartilhada transacao : tp) {
            User u = transacao.getUserId_user();
            if (unica.getTipo().equals("receita")) {
                u.setSaldo(u.getSaldo() + nSvalue);
            } else {
                u.setSaldo(u.getSaldo() - nSvalue);
            }
            UserDAO.save(u);
            TransacaoPartilhadaDAO.save(transacao);
        }
        unica.setShareValue(nSvalue);
        unica.setStatus(true);
        UnicaDAO.save(unica);

        return 0;
    }

    public int giveUpTransactionUnica(String email,int idTransacao) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        String condition;
        try {
            User user = gerirUtilizador.getUserByEmail(email);
            if (user == null) {
                t.rollback();
                return -3;
            }

            Unica unica = UnicaDAO.getUnicaByORMID(idTransacao);
            if (unica == null ) {
                t.rollback();
                return -1;
            }

            if (unica.getOwner_id() == user) {
                //vai apagar a transacao principal
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                Comentario[] comments = ComentarioDAO.listComentarioByQuery(condition, null);

                for (Comentario c : comments) {
                    ComentarioDAO.deleteAndDissociate(c);
                }
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

                //devolver/retirar dinheiro da carteira de todos
                if (unica.getTipo().equals("receita")) {
                    user.setSaldo(user.getSaldo() - unica.getShareValue());
                } else {
                    user.setSaldo(user.getSaldo() + unica.getShareValue());
                }
                UserDAO.save(user);

                for (TransacaoPartilhada tp : tps) {
                    User u = tp.getUserId_user();
                    if (unica.getTipo().equals("receita")) {
                        u.setSaldo(u.getSaldo() - unica.getShareValue());
                    } else {
                        u.setSaldo(u.getSaldo() + unica.getShareValue());
                    }
                    UserDAO.save(u);
                    TransacaoPartilhadaDAO.deleteAndDissociate(tp);

                    //TODO: notificar todos os utilizadores (u) que um o owner eliminou esta  transacao
                    // NOTA: não esquecer de enviar notificação para toda os utilizadores(pedir para se querem continuar mas agora vao pagar/receber (nSValue)) e o proprio a dizer que disistiu
                }

                //mudar o owner para null para assim "eliminar" a transacao
                unica.setOwner_id(null);
                unica.setStatus(false);
                UnicaDAO.save(unica);
                //TODO: notificar o owner (user) eliminou esta  transacao


            } else{
                t.rollback();
                return -2;
            }

            t.commit();
        } catch (Exception e){
            t.rollback();
            return -1;
        }
        return 0;
    }

}
