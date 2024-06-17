package com.walletbud;

import jakarta.ws.rs.ApplicationPath;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Application extends jakarta.ws.rs.core.Application {

    private final NotificationScheduler scheduler;

    public Application() {
        scheduler = new NotificationScheduler();
        scheduler.start();

        // Add shutdown hook to stop the scheduler gracefully
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (scheduler != null) {
                scheduler.stop();
            }
        }));
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(Login.class);
        resources.add(Transacao.class);
        resources.add(Categoria.class);
        resources.add(Register.class);
        resources.add(ResetPassword.class);
        resources.add(Utilizador.class);
        resources.add(Comentario.class);
        resources.add(AuthenticationFilter.class);
        resources.add(CORSFilter.class);
        return resources;
    }
}