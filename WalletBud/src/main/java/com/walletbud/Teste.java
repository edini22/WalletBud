package com.walletbud;


import jakarta.enterprise.inject.se.SeContainer;
import jakarta.enterprise.inject.se.SeContainerInitializer;

public class Teste {

    public static void main(String[] args) {

        // Inicializa o CDI
        try (SeContainer container = SeContainerInitializer.newInstance().initialize()) {
            // Obtém uma instância de EventProducer do CDI
            EventProducer eventProducer = container.select(EventProducer.class).get();

            // Dispara um evento
            //eventProducer.fireEvent("Hello, observers!");
        }

    }

}
