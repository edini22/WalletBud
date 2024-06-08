package com.example.walletbud;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class HelloApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(login.class);
        resources.add(Unica.class);
        resources.add(Categoria.class);
        resources.add(register.class);
        resources.add(Utilizador.class);
        resources.add(HelloResource.class);
        resources.add(AuthenticationFilter.class);
        return resources;
    }
}