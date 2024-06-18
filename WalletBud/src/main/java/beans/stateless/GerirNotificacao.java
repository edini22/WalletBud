package beans.stateless;

import java.time.LocalDateTime;

import jakarta.ejb.EJB;
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

    @EJB
    private GerirUtilizador gerirUtilizador;

    @EJB
    private GerirNotificacao gerirNotificacao;


    public static void clearOldNotificacoes() throws PersistentException {
        try {
            System.out.println("Clearing old notificacoes");
            Notificacao[] notifs = NotificacaoDAO.listNotificacaoByQuery(null, null);
            LocalDateTime now = LocalDateTime.now();

            for (Notificacao n : notifs)
            {
                LocalDateTime notificationDate = n.getDate().toLocalDateTime();
                LocalDateTime thresholdDate = notificationDate.plusDays(10);

                System.out.println(notificationDate + " " + thresholdDate);
                if (now.isAfter(thresholdDate)) {
                    // delete notifs
                    System.out.println("Deleting old notificacoes");
                    NotificacaoDAO.deleteAndDissociate(n);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
