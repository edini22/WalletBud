package com.walletbud;

import jakarta.ejb.Stateful;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Event;
import jakarta.inject.Inject;

@ApplicationScoped
public class EventProducer {

    @Inject
    private Event<String> event; // Evento que envia strings

    public void fireEvent(String message) {
        event.fire(message); // Dispara o evento com uma mensagem
    }
}
