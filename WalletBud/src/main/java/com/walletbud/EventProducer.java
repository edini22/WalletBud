package com.walletbud;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;
import wb.walletbud.Notificacao;

@ApplicationScoped
public class EventProducer {

    @Inject
    private Event<Notificacao> event;

    public void fireEvent(Notificacao message) {
        System.out.println(message);
        event.fire(message);
    }
}
