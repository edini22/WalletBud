package beans.stateless;

import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.json.*;
import org.orm.PersistentException;
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

    public int createComentario(JsonArray comentarios, User owner, Transacao transacao) throws PersistentException {

        if (!checkUserPermission(owner, transacao)) {
            return -1;
        }

        for (JsonValue userValue : comentarios) {
            String descricao = userValue.toString();

            Comentario comentario = ComentarioDAO.createComentario();
            comentario.setDescrição(descricao);
            comentario.setData(new Timestamp(System.currentTimeMillis()));
            comentario.setUserId_user(owner);
            comentario.setTransacaoId_transacao(transacao);

            ComentarioDAO.save(comentario);
        }

        return 0;
    }

    public int editComentario(String descricao, String email, int id_comentario) throws PersistentException {

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

        return 0;
    }

    public int deleteComentario(int id_comentario, String email) throws PersistentException {

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

        ComentarioDAO.deleteAndDissociate(comentario);

        return 0;
    }

    public JsonObject getComentariosByTransaction(int id_transacao, String email) throws PersistentException {

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
        Comentario[] comentarios = ComentarioDAO.listComentarioByQuery(condition, null);

        if (comentarios.length == 0) {
            return Json.createObjectBuilder()
                    .build();
        }

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();

        for (Comentario comentario : comentarios) {

            JsonArrayBuilder comentaioArrayBuilder = Json.createArrayBuilder();
            JsonObject userJs = Json.createObjectBuilder()
                    .add("id", comentario.getId_comentario())
                    .add("user_email", comentario.getUserId_user().getEmail())
                    .add("timestamp", comentario.getData().toString())
                    .add("descricao", comentario.getDescrição())
                    .build();
            comentaioArrayBuilder.add(userJs);
        }

        return Json.createObjectBuilder()
                .add("comentarios", arrayBuilder.build())
                .build();
    }

}
