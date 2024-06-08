package com.walletbud;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ResourceInfo;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import java.io.IOException;
import java.lang.reflect.Method;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        Method method = resourceInfo.getResourceMethod();

        if (method.isAnnotationPresent(Secured.class) || resourceInfo.getResourceClass().isAnnotationPresent(Secured.class)) {
            String authorizationHeader = requestContext.getHeaderString("Authorization");

            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity("Token de autenticação ausente ou inválido").build());
                return;
            }

            String token = authorizationHeader.substring("Bearer".length()).trim();

            try {
                Algorithm algorithm = Algorithm.HMAC256("secret");
                JWTVerifier verifier = JWT.require(algorithm).build();
                DecodedJWT jwt = verifier.verify(token);

//                String email = jwt.getSubject();

//                System.out.println("Email: " + email);

//                requestContext.setProperty("email", email);

            } catch (JWTVerificationException e) {
                requestContext.abortWith(
                        Response.status(Response.Status.UNAUTHORIZED).entity("Token de autenticação inválido").build());
            }
        }
    }
}