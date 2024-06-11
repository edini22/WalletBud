package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.*;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.*;

import java.sql.Timestamp;

@Stateless
public class GerirComentario {

    @EJB
    private GerirUtilizador gerirUtilizador;

    public boolean checkUserPermission(User user, Transacao transacao) throws PersistentException {
        boolean check = false;
        if (transacao.getOwner_id() == user) {
            check = true;
        }

        String condition = "TransacaoId_transacao = " + transacao.getId_transacao();
        TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(condition, null);

        for (TransacaoPartilhada tp : tps) {
            if (tp.getUserId_user() == user) {
                check = true;
            }
        }
        return check;
    }

    public int createComentario(String descricao, User owner, Transacao transacao) throws PersistentException {

        if (!checkUserPermission(owner, transacao)) {
            return -1;
        }

        Comentario comentario = ComentarioDAO.createComentario();
        comentario.setDescrição(descricao);
        comentario.setData(new Timestamp(System.currentTimeMillis()));
        comentario.setUserId_user(owner);
        comentario.setTransacaoId_transacao(transacao);

        ComentarioDAO.save(comentario);

        return 0;
    }

    public int editComentario(String descricao, String email, int id_comentario) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            if (user == null) {
                return -3;
            }

            String condition = "Id_comentario = " + id_comentario;
            Comentario[] comentarios = ComentarioDAO.listComentarioByQuery(condition, null);

            if (comentarios.length == 0) {
                return -1;
            }
            Comentario comentario = comentarios[0];

            if (comentario.getUserId_user() != user) {
                return -2;
            }

            comentario.setData(new Timestamp(System.currentTimeMillis()));
            comentario.setDescrição(descricao);
            ComentarioDAO.save(comentario);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -2;
        }

        return 0;
    }

    public int deleteComentario(int id_comentario, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            if (user == null) {
                return -3;
            }

            String condition = "Id_comentario = " + id_comentario;
            Comentario[] comentarios = ComentarioDAO.listComentarioByQuery(condition, null);

            if (comentarios.length == 0) {
                return -1;
            }

            Comentario comentario = comentarios[0];

            if (comentario.getUserId_user() != user && comentario.getTransacaoId_transacao().getOwner_id() != user) {
                return -2;
            }

            ComentarioDAO.deleteAndDissociate(comentario);
            t.commit();
        } catch (Exception e) {
            t.rollback();
            return -2;
        }
        return 0;
    }

    public JsonObject getComentariosByTransaction(int id_transacao, String email) throws PersistentException {
        PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();
        try {
            User user = gerirUtilizador.getUserByEmail(email);

            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            Transacao transacao = TransacaoDAO.getTransacaoByORMID(id_transacao);

            if (transacao == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            if (!checkUserPermission(user, transacao)) {
                return Json.createObjectBuilder()
                        .build();
            }

            String condition = "TransacaoId_transacao = " + transacao.getId_transacao();
            Comentario[] comentarios = ComentarioDAO.listComentarioByQuery(condition, "Data");


            JsonArrayBuilder comentaioArrayBuilder = Json.createArrayBuilder();
            for (Comentario comentario : comentarios) {
                JsonObject userJs = Json.createObjectBuilder()
                        .add("id", comentario.getId_comentario())
                        .add("user_email", comentario.getUserId_user().getEmail())
                        .add("timestamp", comentario.getData().toString())
                        .add("descricao", comentario.getDescrição())
                        .build();
                comentaioArrayBuilder.add(userJs);
            }

            t.commit();
            return Json.createObjectBuilder()
                    .add("comentarios", comentaioArrayBuilder.build())
                    .build();
        } catch (Exception e) {
            t.rollback();
            return Json.createObjectBuilder()
                    .build();
        }
    }

}
