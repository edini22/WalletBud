package beans.stateless;


import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.*;

import org.orm.PersistentException;
import org.orm.PersistentSession;

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

    public void notify(String description, User user, Transacao unica) throws PersistentException {
        Notificacao notification = new Notificacao();
        notification.setDescrição(description);
        notification.setTransacaoId_transacao(unica);
        notification.setUserId_user(user);
        notification.setDate(new Timestamp(System.currentTimeMillis()));
        NotificacaoDAO.save(notification);
        user.notify(notification);
    }

    public int createUnica(PersistentSession session, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, String email, JsonArray usersArray, String comentario) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) return -3;

            Categoria cat = gerirCategoria.getCategoriaById(session,categoria, email);
            if (cat == null) return -4;

            Unica unica = UnicaDAO.createUnica();
            unica.setName(name);
            unica.setValue(value);
            if(descricao != null && !descricao.trim().isEmpty()) {
                unica.setDescrição(descricao);
            }
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
                int status = gerirTransacaoPartilhada.shareTransaction(session, "unica", email, usersArray, unica, null);
                if (status != 0) {
                    return status;
                }
            }

            if (comentario != null && !comentario.trim().isEmpty()) {
                gerirComentario.createComentario(session, comentario, user, unica);
            }

            UnicaDAO.save(unica);
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }

        return 0;
    }

    public int editUnica(PersistentSession session, int id, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }
            Categoria cat = null;
            if (categoria != -1) {
                cat = gerirCategoria.getCategoriaById(session, categoria, email);
                if (cat == null) {
                    return -4;
                }
            }

            String condition = "Id_transacao = '" + id + "' AND UserId_user = '" + user.getId_user() + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(session, condition, null);

            if (unicas.length == 0) {
                return -1;
            }

            Unica unica = unicas[0];

            if (!unica.getTipo().equals(tipo)) {
                return -1;
            }

            if (name != null) unica.setName(name);
            if (value != -1) {
                //iterar pelos ‘users’ todos partilhados a alterar
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
                if(unica.getValue() != value){
                    float aSvalue = unica.getShareValue();
                    float nSvalue = (value * aSvalue) / unica.getValue();
                    if(tp.length > 0){

                        if (unica.getStatus()) {
                            if (unica.getTipo().equals("receita")) {
                                user.setSaldo(user.getSaldo() - aSvalue);
                            } else {
                                user.setSaldo(user.getSaldo() + aSvalue);
                            }
                            UserDAO.save(user);
                        }

                        
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
        
                            // Notify shared users about the change
                            notify("The transaction " + unica.getName() + " has been updated. Old share value: " + aSvalue + ", new share value: " + nSvalue, u, unica);
                        }

                        // Notify the owner about the successful update
                        notify("Your transaction " + unica.getName() + " has been successfully updated.", user, unica);

                        unica.setStatus(false);
                        unica.setShareValue(nSvalue);

                        unica.setValue(value);
                    } else{
                        if (unica.getStatus()) {
                            if (unica.getTipo().equals("receita")) {
                                user.setSaldo(user.getSaldo() - aSvalue);
                                user.setSaldo(user.getSaldo() + value);
                            } else {
                                user.setSaldo(user.getSaldo() + aSvalue);
                                user.setSaldo(user.getSaldo() - value);
                            }
                            UserDAO.save(user);
                        }
                    }


                }
            }
            if (descricao != null) unica.setDescrição(descricao);
            if (local != null) unica.setLocal(local);
            if (categoria != -1) unica.setCategoriaId_categoria(cat);
            if (time != null) unica.setDate(time);

            UnicaDAO.save(unica);
        } catch (Exception e) {
            e.printStackTrace();
            return -2;
        }
        return 0;
    }


    public JsonObject getUnicas(PersistentSession session, String email, String tipo) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "' AND Tipo = '" + tipo + "'";

            Unica[] unicas = UnicaDAO.listUnicaByQuery(session, condition, null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
            ArrayList<Unica> transacoes_id = new ArrayList<>(Arrays.asList(unicas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Unica u = UnicaDAO.getUnicaByORMID(session, transacaoPartilhada.getUsertransacaoId().getId_transacao());

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
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

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
                            .add("confirma", tpPartilhada.getConfirma())
                            .build();
                    userArrayBuilder.add(userJson);
                }
                JsonArray userArray = userArrayBuilder.build();
                String descricao = unica.getDescrição();
                if(descricao == null) {
                    descricao = "";
                }
                JsonObject unicaJson = Json.createObjectBuilder()
                        .add("id", unica.getId_transacao())
                        .add("name", unica.getName())
                        .add("value", unica.getValue())
                        .add("shareValue", unica.getShareValue())
                        .add("date", unica.getDate().toString())
                        .add("descricao", descricao)
                        .add("categoria", unica.getCategoriaId_categoria().getName())
                        .add("status", unica.getStatus())
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

    public JsonObject getJsonUnicaById(PersistentSession session, int id, String email, String tipo) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "Id_transacao = " + id + " AND Tipo = '" + tipo + "'";
            Unica[] unicas = UnicaDAO.listUnicaByQuery(session, condition, null); // ate aqui qualuer um pode requisitar esta transacao


            if (unicas.length == 0) {
                return Json.createObjectBuilder()
                        .build();
            }

            Unica unica = unicas[0];

            condition = "TransacaoId_transacao = " + id;
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
            //verificar se o owner o se com quem está transacao esta partilhada corresponde ao ‘user’ que pede, senao se verificar return vazio
            if (!(unica.getOwner_id() == user || checkTransationAcess( user, tp))) {
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
            String descricao = unica.getDescrição();
            if(descricao == null) {
                descricao = "";
            }
            JsonObject unicaJson = Json.createObjectBuilder()
                    .add("id", unica.getId_transacao())
                    .add("name", unica.getName())
                    .add("value", unica.getValue())
                    .add("shareValue", unica.getShareValue())
                    .add("date", unica.getDate().toString())
                    .add("descricao", descricao)
                    .add("categoria", unica.getCategoriaId_categoria().getName())
                    .add("status", unica.getStatus())
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

    private boolean checkTransationAcess( User user, TransacaoPartilhada[] tp) {
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


    public int shareUnica(PersistentSession session, JsonArray usersArray, String email, Unica unica) {
        try {

            User user = gerirUtilizador.getUserByEmail(session, email);
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

                User us = gerirUtilizador.getUserByEmail(session, userEmail);
                if (us == null) {
                    return -5;
                }
                //verificar se o user ja esta naquela transacao
                String condition = "TransacaoId_transacao = " + unica.getId_transacao() + " AND UserId_user = " + user.getId_user();
                TransacaoPartilhada[] tpcheck = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
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

    public int editUsersUnica(PersistentSession session, String email, int id_unica, int option, String email_shared) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }

            Unica unica = UnicaDAO.getUnicaByORMID(session, id_unica);
            if (unica == null) {
                return -1;
            }

            if (unica.getOwner_id() != user) {
                return -2;
            }

            String condition = "TransacaoId_transacao = " + unica.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

            int oldUsers = tps.length + 1;

            if (tps.length == 0 && option == -1) {
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
                        // Notificar o utilizador removido
                        notify("You have been removed from the transaction " + unica.getName() + ".",tp.getUserId_user(),unica);
                    }
                }
            }
            if ((cond && option == -1) || (!cond && option == 1)) {

                if (option == 1) {
                    //add user
                    User user_shared = gerirUtilizador.getUserByEmail(session, email);
                    TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                    tp.setUserId_user(user_shared);
                    tp.setUsertransacaoId(unica);
                    TransacaoPartilhadaDAO.save(tp);
                    // Notificar o utilizador adicionado
                    notify("You have been added to the transaction " + unica.getName() + ". Please confirm your participation.", user_shared,unica);
                }

                float aSValue = unica.getShareValue();
                float value = unica.getValue();

                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
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
                }
                if (transacoesPart.length == 0) {
                    confirmUnica(session, unica);
                } else {
                    unica.setStatus(false);
                }
                unica.setShareValue(nSValue);
                UnicaDAO.save(unica);

                // Gerar novas notificações
                for (TransacaoPartilhada tpu : transacoesPart) {
                    User u = tpu.getUserId_user();
                    notify("The transaction " + unica.getName() + " has been updated. Old share value: " + aSValue + ", New share value: " + nSValue + ".",u, unica);
                }

                notify("The transaction " + unica.getName() + " has been successfully updated. Old share value: " + aSValue + ", New share value: " + nSValue + ".", user, unica);

            } else {
                return -4;
            }

        } catch (Exception e) {
            return -1;
        }

        return 0;
    }

    public int handleUnica(PersistentSession session, String email, int id_unica, int option) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }

            Unica unica = UnicaDAO.getUnicaByORMID(session, id_unica);
            if (unica == null || unica.getStatus()) {
                return -1;
            }

            if (unica.getOwner_id() == user) {
                return -2;
            }

            String condition = "TransacaoId_transacao = " + unica.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

            int oldUsers = tps.length + 1;

            if (tps.length == 0) {
                return -5;
            }

            condition = "TransacaoId_transacao = " + unica.getId_transacao() + " AND UserId_user = " + user.getId_user();
            TransacaoPartilhada[] tpcheck = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
            if(tpcheck.length == 0){
                return -4;
            }

            boolean ready_to_confirm = true;
            boolean remove = false;
//            ArrayList<User> users = new ArrayList<>();
            int users = 1;
            boolean exist = false;

            for (TransacaoPartilhada tp : tps) {

                if (tp.getUserId_user() == user) {
                    exist = true;

                    if (option == -1) {
                        if (tp.getConfirma() == 1) {
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
                        unica.setStatus(false);
                        UnicaDAO.save(unica);
                        TransacaoPartilhadaDAO.deleteAndDissociate(tp);
                        // Notify owner that a user has rejected the transaction
                        notify("User " + email + " has rejected the transaction " + unica.getName(),unica.getOwner_id(),unica);

                    } else if (option == 1) {
                        users +=1;
                        tp.setConfirma(1);
                        TransacaoPartilhadaDAO.save(tp);

                        // Notify owner that a user has confirmed the transaction
                        notify("User " + email + " has confirmed the transaction " + unica.getName(),unica.getOwner_id(),unica);
                    }
                } else if (tp.getConfirma() == 0) {
                    users +=1;
                    ready_to_confirm = false;
                } else {
                    if(option == -1){
                        User us = tp.getUserId_user();
                        if (unica.getStatus()) {
                            if (unica.getTipo().equals("receita")) {
                                us.setSaldo(us.getSaldo() - unica.getShareValue());
                            } else {
                                us.setSaldo(us.getSaldo() + unica.getShareValue());
                            }
                        }
                        tp.setConfirma(0);
                        TransacaoPartilhadaDAO.save(tp);
                    }
                    users +=1;
                }
            }
            if (option == -1 && !remove) {
                return -4;
            } else if (remove) {
                //dados novos para a notificacao
                float aSValue = unica.getShareValue();
                float value = unica.getValue();


                float nSValue = value / users;
                unica.setShareValue(nSValue);
                unica.setStatus(false);
                UnicaDAO.save(unica);

                if (unica.getStatus()) {
                    if (unica.getTipo().equals("receita")) {
                        user.setSaldo(user.getSaldo() - unica.getShareValue());
                    } else {
                        user.setSaldo(user.getSaldo() + unica.getShareValue());
                    }
                    UserDAO.save(user);
                }
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
                // Notify remaining users and owner
                for (TransacaoPartilhada tp : transacoesPart) {
                    User sharedUser = gerirUtilizador.getUserByEmail(session,tp.getUserId_user().getEmail());
                    if (sharedUser != null) {
                        notify("Transaction " + unica.getName() + " has been updated. Previous share value: " + aSValue + ", new share value: " + nSValue + ", users remaining: " + users, sharedUser, unica);

                    }
                }
                notify("Transaction " + unica.getName() + " has been updated. Previous share value: " + aSValue + ", new share value: " + nSValue + ", users remaining: " + users, unica.getOwner_id(), unica);

            }
            if (ready_to_confirm && (!remove || users == 1)) {
                System.out.println("confirmaUnica");
                // chamar função para confirmar e atualizar saldos

                confirmUnica(session,unica);

                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

                // Notify all users that the transaction is confirmed
                for (TransacaoPartilhada tp : transacoesPart) {
                    notify("Transaction " + unica.getName() + " is now confirmed.", tp.getUserId_user(), unica);

                }
                notify("Transaction " + unica.getName() + " is now confirmed.", unica.getOwner_id(), unica);


            }

        } catch (Exception e) {
            System.out.println("ardeu ->" + e);
            return -1;
        }

        return 0;
    }

    public int confirmUnica(PersistentSession session, Unica unica) throws PersistentException {

        User user = unica.getOwner_id();

        String condition = "TransacaoId_transacao = " + unica.getId_transacao();
        TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

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

    public int giveUpTransactionUnica(PersistentSession session, String email,int idTransacao) throws PersistentException {
        String condition;
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }

            Unica unica = UnicaDAO.getUnicaByORMID(session, idTransacao);
            if (unica == null ) {
                return -1;
            }

            if (unica.getOwner_id() == user) {
                //vai apagar a transacao principal
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                Comentario[] comments = ComentarioDAO.listComentarioByQuery(session, condition, null);

                for (Comentario c : comments) {
                    ComentarioDAO.deleteAndDissociate(c);
                }
                condition = "TransacaoId_transacao = " + unica.getId_transacao();
                TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

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

                    // Notificar todos os utilizadores (u) que o owner eliminou esta transacao
                    notify("The owner has deleted the transaction " + unica.getName() + ". Please confirm if you want to continue with the new share value.", u, unica);
                }

                //mudar o owner para null para assim "eliminar" a transacao
                unica.setOwner_id(null);
                unica.setStatus(false);
                UnicaDAO.save(unica);

                // Notificar o owner que eliminou esta transacao
                notify("You have successfully deleted the transaction " + unica.getName() + ".",user,unica);

            } else{
                return -2;
            }

        } catch (Exception e){
            return -1;
        }
        return 0;
    }

}
