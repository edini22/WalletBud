package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.*;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import wb.walletbud.*;

import java.sql.Timestamp;

@Stateless
public class GerirComentario {

    @EJB
    private GerirUtilizador gerirUtilizador;

    public boolean checkUserPermission(PersistentSession session, User user, Transacao transacao) throws PersistentException {
        boolean check = false;
        if (transacao.getOwner_id() == user) {
            check = true;
        }

        String condition = "TransacaoId_transacao = " + transacao.getId_transacao();
        TransacaoPartilhada[] tps = TransacaoPartilhadaDAO.listTransacaoPartilhadaByQuery(session, condition, null);

        for (TransacaoPartilhada tp : tps) {
            if (tp.getUserId_user() == user) {
                check = true;
            }
        }
        return check;
    }

    public int createComentario(PersistentSession session, String descricao, User owner, Transacao transacao) throws PersistentException {

        if (!checkUserPermission(session, owner, transacao)) {
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

    public int editComentario(PersistentSession session, String descricao, String email, int id_comentario) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            if (user == null) {
                return -3;
            }

            String condition = "Id_comentario = " + id_comentario;
            Comentario[] comentarios = ComentarioDAO.listComentarioByQuery(session, condition, null);

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
        } catch (Exception e) {
            return -2;
        }

        return 0;
    }

    public int deleteComentario(PersistentSession session, int id_comentario, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            if (user == null) {
                return -3;
            }

            String condition = "Id_comentario = " + id_comentario;
            Comentario[] comentarios = ComentarioDAO.listComentarioByQuery(session, condition, null);

            if (comentarios.length == 0) {
                return -1;
            }

            Comentario comentario = comentarios[0];

            if (comentario.getUserId_user() != user && comentario.getTransacaoId_transacao().getOwner_id() != user) {
                return -2;
            }

            ComentarioDAO.deleteAndDissociate(comentario,session);
        } catch (Exception e) {
            return -2;
        }
        return 0;
    }

    public JsonObject getComentariosByTransaction(PersistentSession session, int id_transacao, String email) throws PersistentException {
        try {
            User user = gerirUtilizador.getUserByEmail(session, email);

            if (user == null) {
                return Json.createObjectBuilder()
                        .build();
            }

            Transacao transacao = TransacaoDAO.getTransacaoByORMID(session, id_transacao);

            if (transacao == null) {
                return Json.createObjectBuilder()
                        .build();
            }

//            if (!checkUserPermission(session, user, transacao)) {
//                return Json.createObjectBuilder()
//                        .build();
//            }

            String condition = "TransacaoId_transacao = " + transacao.getId_transacao();
            Comentario[] comentarios = ComentarioDAO.listComentarioByQuery(session, condition, "Data");


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

            return Json.createObjectBuilder()
                    .add("comentarios", comentaioArrayBuilder.build())
                    .build();
        } catch (Exception e) {
            return Json.createObjectBuilder()
                    .build();
        }
    }

}
