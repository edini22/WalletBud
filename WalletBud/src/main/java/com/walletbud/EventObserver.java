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
import wb.walletbud.User;

import java.util.Map;


@ServerEndpoint("/teste")
@ApplicationScoped
public class EventObserver {

    @Inject
    private SessionRegistry sessionRegistry;


    public void receiveEvent(@Observes String message) {
        System.out.println("Evento recebido: " + message);
        sessionRegistry.getAll().forEach(session -> session.getAsyncRemote().sendText(toJson(message)));
    }

    @OnOpen
    public void open(Session session, EndpointConfig conf) {
        System.out.println("Added");
        sessionRegistry.add(session);
        System.out.println(sessionRegistry.getAll().toString());
    }

    @OnClose
    public void close(Session session, CloseReason reason) {
        System.out.println("Removed");
        sessionRegistry.remove(session);
    }

    private String toJson(String message) {
        final JsonObject jsonObject = Json.createObjectBuilder()
                .add("id", message)
                .add("cooked", "email")
                .build();
        return jsonObject.toString();
    }
}
