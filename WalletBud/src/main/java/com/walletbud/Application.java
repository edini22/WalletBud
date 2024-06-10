package com.walletbud;

import jakarta.ws.rs.ApplicationPath;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class Application extends jakarta.ws.rs.core.Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet<>();
        resources.add(login.class);
        resources.add(Transacao.class);
        resources.add(Categoria.class);
        resources.add(register.class);
        resources.add(Utilizador.class);
        resources.add(AuthenticationFilter.class);
        resources.add(CORSFilter.class);
        return resources;
    }
}