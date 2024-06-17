package com.walletbud;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;
import wb.walletbud.Transacao;
import wb.walletbud.User;
import wb.walletbud.Notificacao;
import beans.stateless.GerirNotificacao;
import beans.stateless.GerirUtilizador;

public class NotificationTask implements Runnable {

    @Override
    public void run() {
        try {
            checkAndSendNotificacoes();
            clearOldNotificacoes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkAndSendNotificacoes() throws PersistentException {

        PersistentSession session = null;
        PersistentTransaction transaction = null;

        try {
            session = AASICPersistentManager.instance().getSession();
            transaction = session.beginTransaction();

            Map<User, List<Transacao>> transacoes = GerirUtilizador.getAllTransacoesFromUsers(session);
            LocalDateTime now = LocalDateTime.now();

            for (User user : transacoes.keySet()) {
                List<Transacao> userTransacoes = transacoes.get(user);

                for (Transacao transacao : userTransacoes) {

                    Notificacao notification = new Notificacao();
                    notification.setTransacaoId_transacao(transacao);
                    notification.setUserId_user(user);
                    notification.setDate(Timestamp.valueOf(now));
                    notification.setDescrição("Tem uma transação dentro de 3 dias: " + transacao.getName() + " on " + transacao.getDate());
                    user.notify(notification);
                }
            }
            transaction.commit();
        } catch (Exception e) {
            if( transaction != null)
                transaction.rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    private void clearOldNotificacoes() throws PersistentException {
        // Get all Notificacaos older than 10 days
        GerirNotificacao.clearOldNotificacoes();
    }
}
