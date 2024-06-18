package com.walletbud;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;
import wb.walletbud.Notificacao;
import wb.walletbud.NotificacaoDAO;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@ServerEndpoint("/notifications")
public class NotificationWebSocket {

    private static Map<Long, Set<Session>> userSessions = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) throws PersistentException {
        Long userId = getUserIdFromSession(session);
        System.out.println("chegou um pedido para entar no ws");
        if (userId != null) {
            userSessions.putIfAbsent(userId, new HashSet<>());
            userSessions.get(userId).add(session);
            sendPreviousNotifications(session, userId);
            System.out.println("New session opened: " + session.getId());
        }
    }

    @OnClose
    public void onClose(Session session) {
        Long userId = getUserIdFromSession(session);
        if (userId != null) {
            Set<Session> sessions = userSessions.get(userId);
            if (sessions != null) {
                sessions.remove(session);
                if (sessions.isEmpty()) {
                    userSessions.remove(userId);
                }
            }
            System.out.println("Session closed: " + session.getId());
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        // Pode ser implementada lógica adicional se necessário
    }

    public static void sendNotification(Long userId, Notificacao notification) {
        JsonObjectBuilder notifJsonObjectBuilder = Json.createObjectBuilder()
                .add("id", notification.getId_notificacao())
                .add("date", notification.getDate().toString())
                .add("descricao", notification.getDescrição());

        JsonObject notifJson = notifJsonObjectBuilder.build();

        Set<Session> sessions = userSessions.get(userId);
        if (sessions != null) {
            sessions.forEach(session -> {
                try {
                    session.getBasicRemote().sendText(notifJson.toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private void sendPreviousNotifications(Session session, Long userId) throws PersistentException {
        // Lógica para enviar notificações anteriores ao usuário quando ele se conecta
        String condition = "UserId_user = " + userId;
        PersistentSession ses = null;
        PersistentTransaction transaction = null;

        try {
            ses = AASICPersistentManager.instance().getSession();
            transaction = ses.beginTransaction();

            Notificacao[] notificacaos = NotificacaoDAO.listNotificacaoByQuery(ses,condition,null);

            for (Notificacao notificacao : notificacaos) {
                session.getBasicRemote().sendText(notificacao.toString());
            }


            transaction.commit();
        } catch (Exception e) {
            if( transaction != null)
                transaction.rollback();
        } finally {
            if (ses != null && ses.isOpen()) {
                ses.close();
            }
        }
    }

    private Long getUserIdFromSession(Session session) {
        // Implemente a lógica para obter o userId da sessão, por exemplo, a partir dos parâmetros da query string
        // Neste exemplo, estamos supondo que o userId está presente na query string
        // Você pode adaptar isso de acordo com sua implementação específica
        try {
            String queryString = session.getQueryString();
            if (queryString != null && queryString.startsWith("userId=")) {
                return Long.parseLong(queryString.substring(7));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return null;
    }
}


