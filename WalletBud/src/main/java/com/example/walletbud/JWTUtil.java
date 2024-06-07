package com.example.walletbud;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTUtil {

    private static final String SECRET_KEY = "secret";

    public static String getEmailFromToken(String token) {
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET_KEY))
                    .build()
                    .verify(token);
            return jwt.getSubject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}

