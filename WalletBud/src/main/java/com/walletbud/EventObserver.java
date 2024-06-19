package com.walletbud;

import beans.stateless.GerirUtilizador;
import beans.stateless.SessionRegistry;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.inject.Inject;
import org.orm.PersistentException;
import org.orm.PersistentSession;
import org.orm.PersistentTransaction;
import wb.walletbud.AASICPersistentManager;
import wb.walletbud.Notificacao;
import wb.walletbud.User;

import java.util.Map;


@ServerEndpoint("/notifs")
@ApplicationScoped
public class EventObserver {

    @Inject
    private SessionRegistry sessionRegistry;

    @EJB
    private GerirUtilizador gerirUtilizador;

    public void receiveEvent(@Observes Notificacao message) {
        System.out.println("Evento recebido: " + message);

        Map<Session, User> sessions = sessionRegistry.getAll();

        for (Map.Entry<Session, User> entry : sessions.entrySet()) {
            Session session = entry.getKey();
            User user = entry.getValue();
            System.out.println("User notif: " + message.getUserId_user() + " , user token: " + user.getId_user());
            if (user.getId_user() == message.getUserId_user().getId_user()) {
                session.getAsyncRemote().sendText(toJson(message, user.getEmail()));
                break;
            }
        }

    }

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        System.out.println("Added");
        sessionRegistry.add(session, null);
        System.out.println(sessionRegistry.getAll().toString());
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("Removed");
        sessionRegistry.remove(session);
    }

    @OnMessage
    public void onMessage(String message, Session session) throws PersistentException {
        System.out.println("Received message: " + message);

        String email = JWTUtil.getEmailFromToken(message);

        PersistentSession psession = null;
        PersistentTransaction transaction = null;
        User user = null;
        try{
            psession = AASICPersistentManager.instance().getSession();
            transaction = psession.beginTransaction();
            user = gerirUtilizador.getUserByEmail(psession, email);
            transaction.commit();
        } catch (Exception e) {
            if( transaction != null)
                transaction.rollback();
            System.out.println("Error: " + e.getMessage());
            sessionRegistry.add(session, user);
            System.out.println(sessionRegistry.getAll().toString());
        } finally {
            if (psession != null && psession.isOpen()) {
                psession.close();
            }
        }

        sessionRegistry.add(session, user);
    }

    private String toJson(Notificacao message, String email) {
        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("descricao", message.getDescrição())
                .add("data", message.getDate().toString())
                .add("email", email)
                .build();
        return jsonObject.toString();
    }
}