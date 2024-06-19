package beans.stateless;


import com.walletbud.EventProducer;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;
import jakarta.json.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import wb.walletbud.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Iterator;
import java.util.List;

@Stateless
public class GerirFixa {

    @EJB
    private GerirUtilizador gerirUtilizador;

    @EJB
    private GerirCategoria gerirCategoria;

    @EJB
    private GerirTransacaoPartilhada gerirTransacaoPartilhada;

    @EJB
    private GerirComentario gerirComentario;

    @Inject
    private EventProducer eventProducer;

    public void notify(String description, User user, Transacao fixa) throws PersistentException{
        Notificacao notification = new Notificacao();
        notification.setDescrição(description);
        notification.setTransacaoId_transacao(fixa);
        notification.setUserId_user(user);
        notification.setDate(new Timestamp(System.currentTimeMillis()));
        NotificacaoDAO.save(notification);
        user.notify(notification);
        eventProducer.fireEvent(notification);
    }

    public int createFixa(PersistentSession session, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, int repeticao, String email, JsonArray usersArray, String comentario) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) return -3;

            Categoria cat = gerirCategoria.getCategoriaById(session, categoria, email);
            if (cat == null) return -4;

            Fixa fixa = FixaDAO.createFixa();
            fixa.setName(name);
            fixa.setValue(value);
            if(descricao != null && !descricao.trim().isEmpty()) {
                fixa.setDescrição(descricao);
            }
            fixa.setLocal(local);
            fixa.setTipo(tipo);
            fixa.setCategoriaId_categoria(cat);
            fixa.setDate(time);
            fixa.setOwner_id(user);
            fixa.setRepeticao(repeticao);

            if (usersArray.isEmpty()) {
                fixa.setShareValue(value);
                fixa.setStatus(true);
            } else {
                int status = gerirTransacaoPartilhada.shareTransaction(session, "fixa", email, usersArray, null, fixa);
                if (status != 0) {
                    System.out.println("status: " + status);
                    return status;
                }
            }

            if (comentario != null && !comentario.trim().isEmpty()) {
                gerirComentario.createComentario(session, comentario, user, fixa);
            }

            FixaDAO.save(fixa);

        } catch (Exception e) {
            return -2;
        }

        return 0;
    }

    public int editFixa(PersistentSession session, int id, String name, float value, String descricao, String local, String tipo, int categoria, Timestamp time, int repeticao, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }
            Categoria cat = null;
            if (categoria != -1) {
                cat = gerirCategoria.getCategoriaById(session, categoria, email);
                if (cat == null){
                    return -4;
                }
            }

            String condition = "Id_transacao = '" + id + "' AND UserId_user = '" + user.getId_user() + "'";
            Fixa[] fixas = FixaDAO.listFixaByQuery(session, condition, null);

            if (fixas.length == 0) {
                return -1;
            }

            Fixa fixa = fixas[0];

            if (!fixa.getTipo().equals(tipo)) {
                return -1;
            }

            if (name != null) fixa.setName(name);
            if (value != -1) {
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
                if(fixa.getValue() != value ) {
                    float aSvalue = fixa.getShareValue();
                    float nSvalue = (value * aSvalue) / fixa.getValue();

                    //iterar pelos ‘users’ todos partilhados a alterar

                    for (TransacaoPartilhada transacao : tp) {
                        User sharedUser = gerirUtilizador.getUserByEmail(session,transacao.getUserId_user().getEmail());
                        transacao.setConfirma(0);
                        TransacaoPartilhadaDAO.save(transacao);
                        if (sharedUser != null) {
                            notify("Transaction " + fixa.getName() + " has been edited. Old share value: " + aSvalue + ", new share value: " + nSvalue, sharedUser, fixa);
                        }
                    }

                    if(tp.length > 0){
                        fixa.setStatus(false);
                    }
                    fixa.setShareValue(nSvalue);

                    fixa.setValue(value);
                }
            }
            if (descricao != null) fixa.setDescrição(descricao);
            if (local != null) fixa.setLocal(local);
            if (categoria != -1) fixa.setCategoriaId_categoria(cat);
            if (repeticao != -1) fixa.setRepeticao(repeticao);
            if (time != null) fixa.setDate(time);


            FixaDAO.save(fixa);

            notify("Your transaction " + fixa.getName() + " has been successfully edited.",user, fixa);
        } catch (Exception e) {
            return -2;
        }
        return 0;
    }

    public JsonObject getFixas(PersistentSession session, String email, String tipo) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "UserId_user = '" + user.getId_user() + "' AND Tipo = '" + tipo + "'";

            Fixa[] fixas = FixaDAO.listFixaByQuery(session, condition, null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Fixa f = FixaDAO.getFixaByORMID(session, transacaoPartilhada.getUsertransacaoId().getId_transacao());

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
                TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

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
                            .add("confirma", tpPartilhada.getConfirma())
                            .build();
                    userArrayBuilder.add(userJson);
                }
                JsonArray userArray = userArrayBuilder.build();
                String descricao = fixa.getDescrição();
                if(descricao == null) {
                    descricao = "";
                }
                JsonObject unicaJson = Json.createObjectBuilder()
                        .add("id", fixa.getId_transacao())
                        .add("name", fixa.getName())
                        .add("value", fixa.getValue())
                        .add("shareValue", fixa.getShareValue())
                        .add("date", fixa.getDate().toString())
                        .add("descricao", descricao)
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

    public JsonObject getJsonFixaById(PersistentSession session, int id, String email, String tipo) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "Id_transacao = " + id + " AND Tipo = '" + tipo + "'";
            Fixa[] fixas = FixaDAO.listFixaByQuery(session, condition, null);


            if (fixas.length == 0) {
                return Json.createObjectBuilder()
                        .build();
            }

            Fixa fixa = fixas[0];

            condition = "TransacaoId_transacao = " + id;
            TransacaoPartilhada[] tp = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

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
                    .add("confirma", 1)
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
            String descricao = fixa.getDescrição();
            if(descricao == null) {
                descricao = "";
            }
            return Json.createObjectBuilder()
                    .add("id", fixa.getId_transacao())
                    .add("name", fixa.getName())
                    .add("value", fixa.getValue())
                    .add("shareValue", fixa.getShareValue())
                    .add("date", fixa.getDate().toString())
                    .add("descricao", descricao)
                    .add("categoria", fixa.getCategoriaId_categoria().getName())
                    .add("status", fixa.getStatus())
                    .add("repeticao", fixa.getRepeticao())
                    .add("tipo", fixa.getTipo())
                    .add("local", fixa.getLocal())
                    .add("status", fixa.getStatus())
                    .add("users", userArray)
                    .build();

        } catch (Exception e) {
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

    public int shareFixa(PersistentSession session, JsonArray usersArray, String email, Fixa fixa) {
        try {

            User user = gerirUtilizador.getUserByEmail(session, email);
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

                User us = gerirUtilizador.getUserByEmail(session, userEmail);
                if (us == null) {
                    return -5;
                }

                //verificar se o user ja esta naquela transacao
                String condition = "TransacaoId_transacao = " + fixa.getId_transacao() + " AND UserId_user = " + user.getId_user();
                TransacaoPartilhada[] tpcheck = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
                if(tpcheck.length != 0){
                    return -5;
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
            return -1;
        }
        return 0;
    }

    public int editUsersFixa(PersistentSession session, String email, int id_fixa, int option, String email_shared) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }

            Fixa fixa = FixaDAO.getFixaByORMID(session, id_fixa);
            if (fixa == null) {
                return -1;
            }

            if (fixa.getOwner_id() != user) {
                return -2;
            }

            String condition = "TransacaoId_transacao = " + fixa.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

            if (tps.length == 0 && option == -1) {
                return -4;
            }

            boolean cond = false;

            for (TransacaoPartilhada tp : tps) {

                if (tp.getUserId_user().getEmail().equals(email_shared)) {
                    cond = true;
                    if (option == -1) {
                        TransacaoPartilhadaDAO.deleteAndDissociate(tp);
                        //notificar que user rejeitou a transacao
                        notify("User " + email_shared + " has been removed from the transaction " + fixa.getName(),user,fixa);
                    }
                }
            }
            if ((cond && option == -1) || (!cond && option == 1)) {

                if (option == 1) {
                    //add user
                    User user_shared = gerirUtilizador.getUserByEmail(session, email_shared);
                    TransacaoPartilhada tp = TransacaoPartilhadaDAO.createTransacaoPartilhada();
                    tp.setUserId_user(user_shared);
                    tp.setUsertransacaoId(fixa);
                    TransacaoPartilhadaDAO.save(tp);

                    // Notify that a user was added to the transaction
                    notify("User " + email_shared + " has been added to the transaction " + fixa.getName(),user,fixa);
                }

                float aSValue = fixa.getShareValue();
                float value = fixa.getValue();

                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
                int nUsers = transacoesPart.length + 1;

                float nSValue = value / nUsers;

                if (fixa.getStatus()) {
                    for (TransacaoPartilhada tpu : transacoesPart) {
                        tpu.setConfirma(0);
                        TransacaoPartilhadaDAO.save(tpu);
                    }
                }

                fixa.setStatus(transacoesPart.length == 0);
                fixa.setShareValue(nSValue);
                FixaDAO.save(fixa);
                

                // Notify all users involved in the transaction
                for (TransacaoPartilhada tp : transacoesPart) {
                    User sharedUser = tp.getUserId_user();
                    if (sharedUser != null) {
                        notify("Transaction " + fixa.getName() + " has been updated. Previous share value: " + aSValue + ", new share value: " + nSValue + ", total users: " + nUsers, sharedUser, fixa);
                    }
                }

                // Notify the owner
                notify("Transaction " + fixa.getName() + " has been updated. Previous share value: " + aSValue + ", new share value: " + nSValue + ", total users: " + nUsers, user, fixa);

            } else {
                return -4;
            }
        } catch (Exception e) {
            return -1;
        }

        return 0;
    }

    public int handleFixa(PersistentSession session, String email, int id_fixa, int option) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }

            Fixa fixa = FixaDAO.getFixaByORMID(session, id_fixa);
            if (fixa == null || fixa.getStatus()) {
                return -1;
            }

            if (fixa.getOwner_id() == user) {
                return -2;
            }

            String condition = "TransacaoId_transacao = " + fixa.getId_transacao();
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

            if (tps.length == 0) {
                return -5;
            }

            condition = "TransacaoId_transacao = " + fixa.getId_transacao() + " AND UserId_user = " + user.getId_user();
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
                        TransacaoPartilhadaDAO.deleteAndDissociate(tp);

                        // Notify owner that a user has rejected the transaction
                        notify("User " + email + " has rejected the transaction " + fixa.getName(), fixa.getOwner_id(), fixa);

                        fixa.setStatus(false);
                        FixaDAO.save(fixa);

                    } else if (option == 1) {
                        users +=1;
                        tp.setConfirma(1);
                        TransacaoPartilhadaDAO.save(tp);

                        // Notify owner that a user has confirmed the transaction
                        notify("User " + email + " has confirmed the transaction " + fixa.getName(),fixa.getOwner_id(),fixa);
                    }
                } else if (tp.getConfirma() == 0) {
                    users +=1;
                    ready_to_confirm = false;
                } else {
                    if(option == -1){
                        tp.setConfirma(0);
                        TransacaoPartilhadaDAO.save(tp);
                    }
                    users +=1;
                }
            }
            if(exist == false){
                //user nao pertence a transacao
                return -1;
            } else if (option == -1 && !remove) {
                return -4;
            } else if (remove) {
                //dados novos para a notificacao
                float aSValue = fixa.getShareValue();
                float value = fixa.getValue();

                float nSValue = value / users;
                fixa.setShareValue(nSValue);
                fixa.setStatus(false);
                FixaDAO.save(fixa);

                System.out.println("nUsers = " + users);
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

                // Notify remaining users and owner
                for (TransacaoPartilhada tp : transacoesPart) {
                    User sharedUser = gerirUtilizador.getUserByEmail(session,tp.getUserId_user().getEmail());
                    if (sharedUser != null) {
                        notify("Transaction " + fixa.getName() + " has been updated. Previous share value: " + aSValue + ", new share value: " + nSValue + ", users remaining: " + users, sharedUser, fixa);
                    }
                }
                notify("Transaction " + fixa.getName() + " has been updated. Previous share value: " + aSValue + ", new share value: " + nSValue + ", users remaining: " + users, fixa.getOwner_id(), fixa);
            }
            if (ready_to_confirm && (!remove || users == 1)) {
                fixa.setStatus(true);
                float nSValue = fixa.getValue() / users;
                fixa.setShareValue(nSValue);
                FixaDAO.save(fixa);
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] transacoesPart = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

                // Notify all users that the transaction is confirmed

                for(TransacaoPartilhada tp : transacoesPart){
                    User u = tp.getUserId_user();
                    notify("Transaction " + fixa.getName() + " is now confirmed. Share value: " + nSValue + ", total users: " + users, u, fixa);

                }
                notify("Transaction " + fixa.getName() + " is now confirmed. Share value: " + nSValue + ", total users: " + users,fixa.getOwner_id(),fixa);

            }

        } catch (Exception e) {
            return -1;
        }

        return 0;
    }

    public int payFixa(PersistentSession session, String email, int id_fixa, Timestamp dateNow, Timestamp date) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }

            Fixa fixa = FixaDAO.getFixaByORMID(session, id_fixa);
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
            TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

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

            // Notify the owner
            notify("Your fixed transaction " + fixa.getName() + " has been paid.", user, fixa);

            for (TransacaoPartilhada tp : tps) {
                User u = tp.getUserId_user();
                u.transacaoFixa.add(tf);
                if (fixa.getTipo().equals("receita")) {
                    u.setSaldo(u.getSaldo() + fixa.getShareValue());
                } else {
                    u.setSaldo(u.getSaldo() - fixa.getShareValue());
                }
                UserDAO.save(u);

                //notificar todos os utilizadores (u) que uma despes/receita fixa foi paga
                notify("The fixed transaction " + fixa.getName() + " has been paid.", u, fixa);
            }

        } catch (Exception e){
            return -1;
        }
        return 0;
    }

    public JsonObject getJsonPaymentFixa(PersistentSession session, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            JsonArrayBuilder userArrayBuilder = Json.createArrayBuilder();

            for (Iterator it = user.transacaoFixa.getIterator(); it.hasNext();) {
                TransacaoFixa tf = (TransacaoFixa) it.next();
                String descricao = tf.getTransacaofixa_ID().getDescrição();
                if(descricao == null) {
                    descricao = "";
                }
                JsonObject userJson = Json.createObjectBuilder()
                        .add("id", tf.getTransacaofixa_ID().getId_transacao())
                        .add("name", tf.getTransacaofixa_ID().getName())
                        .add("value", tf.getPayvalue())
                        .add("Data",tf.getDataAtual().toString())
                        .add("Data Pagamento",tf.getDataPagamento().toString())
                        .add("descricao", descricao)
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

    public int giveUpTransactionFixa(PersistentSession session, String email,int idTransacao) throws PersistentException {
        String condition;
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);
            if (user == null) {
                return -3;
            }

            Fixa fixa = FixaDAO.getFixaByORMID(session, idTransacao);
            if (fixa == null ) {
                return -1;
            }

            if (fixa.getOwner_id() == user) {
                //vai apagar a transacao principal
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                Comentario[] comments = ComentarioDAO.listComentarioByQuery(session, condition, null);

                for (Comentario c : comments) {
                    ComentarioDAO.deleteAndDissociate(c);
                }

                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

                for (TransacaoPartilhada tp : tps) {
                    User u = tp.getUserId_user();
                    TransacaoPartilhadaDAO.deleteAndDissociate(tp);

                    //notificar todos os utilizadores (u) que o owner eliminou esta  transacao
                    notify("The owner has deleted the fixed transaction " + fixa.getName() + ".",u,null);
                }

                //verificar se existem pagamentos associados
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoFixa[] tpfixas = TransacaoFixaDAO.listTransacaoFixaByQuery(session, condition, null);

                if(tpfixas.length == 0){
                    FixaDAO.deleteAndDissociate(fixa);
                } else{
                    //mudar o owner para null para assim "eliminar" a transacao
                    fixa.setOwner_id(null);
                    FixaDAO.save(fixa);
                }

                //notificar o owner (user) eliminou esta  transacao
                notify("You have deleted the fixed transaction " + fixa.getName() + ".", user,null);

            } else{
                // verifica se está associado a esta transacao
                condition = "TransacaoId_transacao = " + fixa.getId_transacao() + " AND UserId_user = " + user.getId_user();
                TransacaoPartilhada[] tpcheck = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
                if(tpcheck.length == 0){
                    return -4;
                }
                //remove a subscricao
                TransacaoPartilhadaDAO.deleteAndDissociate(tpcheck[0]);


                // mete o estado a false e o confirma a 0 e notifica a todos para aceitar
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

                if(tps.length != 0){
                    for (TransacaoPartilhada tp : tps) {
                        tp.setConfirma(0);
                        TransacaoPartilhadaDAO.save(tp);

                        User u = tp.getUserId_user();
                        float nSValue = fixa.getValue()/tps.length;

                        // Notify all users that a user has given up
                        notify("A user has given up the fixed transaction " + fixa.getName() + ".",u,fixa);

                        // Inform users to decide on continuing with the new share value
                        notify("Do you want to continue with the new share value of " + nSValue + "?",u,fixa);
                    }
                    fixa.setStatus(false);
                    FixaDAO.save(fixa);
                }

            }
        } catch (Exception e){
            return -1;
        }
        return 0;
    }

    public JsonObject getLatePayments(PersistentSession session, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            String condition = "UserId_user = " + user.getId_user() + " AND Status = TRUE";

            Fixa[] fixas = FixaDAO.listFixaByQuery(session, condition, null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Fixa f = FixaDAO.getFixaByORMID(session, transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if (f != null && f.getStatus()) {
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

                TransacaoFixa[] tfs = TransacaoFixaDAO.listTransacaoFixaByQuery(session, condition, orederby);
                Timestamp time ;
                if (tfs.length == 0) {
                    time = new Timestamp(fixa.getDate().getTime());
                } else {
                    time = new Timestamp(tfs[0].getDataPagamento().getTime());
                    LocalDateTime adjustedDateTime = time.toLocalDateTime();

                    // Adicionar a repetição apropriada
                    switch (fixa.getRepeticao()) {
                        case 1: // Diariamente
                            adjustedDateTime = adjustedDateTime.plusDays(1);
                            break;
                        case 2: // Semanalmente
                            adjustedDateTime = adjustedDateTime.plusWeeks(1);
                            break;
                        case 3: // Mensalmente
                            adjustedDateTime = adjustedDateTime.plusMonths(1);
                            break;
                        case 4: // Anualmente
                            adjustedDateTime = adjustedDateTime.plusYears(1);
                            break;
                    }

                    // Converter de volta para Timestamp
                    time = Timestamp.valueOf(adjustedDateTime);
                }

                while(curTime.after(time)){

                    JsonObject lateJson = Json.createObjectBuilder()
                            .add("id",fixa.getId_transacao())
                            .add("name", fixa.getName())
                            .add("value", fixa.getValue())
                            .add("shareValue", fixa.getShareValue())
                            .add("categoria", fixa.getCategoriaId_categoria().getName())
                            .add("repeticao", fixa.getRepeticao())
                            .add("tipo", fixa.getTipo())
                            .add("date", time.toString())
                            .build();

                    lateArrayBuilder.add(lateJson);

                    LocalDateTime adjustedDateTime = time.toLocalDateTime();

                    // Adicionar a repetição apropriada
                    switch (fixa.getRepeticao()) {
                        case 1: // Diariamente
                            adjustedDateTime = adjustedDateTime.plusDays(1);
                            break;
                        case 2: // Semanalmente
                            adjustedDateTime = adjustedDateTime.plusWeeks(1);
                            break;
                        case 3: // Mensalmente
                            adjustedDateTime = adjustedDateTime.plusMonths(1);
                            break;
                        case 4: // Anualmente
                            adjustedDateTime = adjustedDateTime.plusYears(1);
                            break;
                    }

                    time = Timestamp.valueOf(adjustedDateTime);
                }

            }
            return Json.createObjectBuilder()
                    .add("atrasos", lateArrayBuilder)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }
    }

    public JsonObject getPayments(PersistentSession session, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            String condition = "UserId_user = " + user.getId_user() + " AND Status = TRUE";

            Fixa[] fixas = FixaDAO.listFixaByQuery(session, condition, null);

            condition = "UserId_user = " + user.getId_user();
            TransacaoPartilhada[] transacoes_partilhada = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);
            ArrayList<Fixa> transacoes_id = new ArrayList<>(Arrays.asList(fixas));

            for (TransacaoPartilhada transacaoPartilhada : transacoes_partilhada) {
                try {
                    Fixa f = FixaDAO.getFixaByORMID(session, transacaoPartilhada.getUsertransacaoId().getId_transacao());

                    if (f != null && f.getStatus()) {
                        transacoes_id.add(f);
                    }
                } catch (Exception e) {
                    //nao era fixa
                }
            }

            String orederby = "";

            JsonArrayBuilder lateArrayBuilder = Json.createArrayBuilder();

            for (Fixa fixa : transacoes_id) {
                condition = "TransacaoId_transacao = " + fixa.getId_transacao();
                orederby = "DataPagamento DESC";

                TransacaoFixa[] tfs = TransacaoFixaDAO.listTransacaoFixaByQuery(session, condition, orederby);
                Timestamp time ;
                if (tfs.length == 0) {
                    time = new Timestamp(fixa.getDate().getTime());
                } else {
                    time = new Timestamp(tfs[0].getDataPagamento().getTime());
                    LocalDateTime adjustedDateTime = time.toLocalDateTime();

                    // Adicionar a repetição apropriada
                    switch (fixa.getRepeticao()) {
                        case 1: // Diariamente
                            adjustedDateTime = adjustedDateTime.plusDays(1);
                            break;
                        case 2: // Semanalmente
                            adjustedDateTime = adjustedDateTime.plusWeeks(1);
                            break;
                        case 3: // Mensalmente
                            adjustedDateTime = adjustedDateTime.plusMonths(1);
                            break;
                        case 4: // Anualmente
                            adjustedDateTime = adjustedDateTime.plusYears(1);
                            break;
                    }

                    // Converter de volta para Timestamp
                    time = Timestamp.valueOf(adjustedDateTime);
                }

                JsonObject lateJson = Json.createObjectBuilder()
                        .add("id",fixa.getId_transacao())
                        .add("name", fixa.getName())
                        .add("value", fixa.getValue())
                        .add("sharevalue", fixa.getShareValue())
                        .add("categoria", fixa.getCategoriaId_categoria().getName())
                        .add("repeticao", fixa.getRepeticao())
                        .add("tipo", fixa.getTipo())
                        .add("date", time.toString())
                        .build();

                lateArrayBuilder.add(lateJson);
            }

            return Json.createObjectBuilder()
                    .add("proximos_pagamentos", lateArrayBuilder)
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Json.createObjectBuilder()
                    .build();
        }
    }

}
