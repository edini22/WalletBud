package beans.stateless;

import java.time.LocalDateTime;
import jakarta.ejb.Stateless;
import org.orm.PersistentException;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;
import wb.walletbud.Notificacao;
import wb.walletbud.NotificacaoDAO;
import wb.walletbud.Transacao;
import wb.walletbud.User;

@Stateless
public class GerirNotificacao {

    public static void clearOldNotificacoes() throws PersistentException {
        try {
            Notificacao[] notifs = NotificacaoDAO.listNotificacaoByQuery(null, null);
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime thresholdDate = now.plusDays(10);
            
            for (Notificacao n : notifs)
            {
                LocalDateTime notificationDate = n.getDate().toLocalDateTime();
                if (notificationDate.isAfter(thresholdDate)) {
                    // delete notifs
                    deleteNotification(n.getTransacaoId_transacao(),n.getUserId_user());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void deleteNotification(Transacao transacao, User user) {
        try {
            PersistentTransaction t = AASICPersistentManager.instance().getSession().beginTransaction();

            // Assuming you have a DAO or ORM method to delete the notification
            Notificacao[] notificacoes = NotificacaoDAO.listNotificacaoByQuery("transacaoId_transacao = " + transacao.getId_transacao() + " AND userId_user = " + user.getId_user(), null);
            
            if (notificacoes.length > 0) {
                NotificacaoDAO.delete(notificacoes[0]);
                t.commit();
            } else {
                System.out.println("Notification not found for deletion.");
            }
        } catch (PersistentException e) {
            System.err.println("Error deleting notification: " + e.getMessage());
            e.printStackTrace();
        }
    }


    
}
