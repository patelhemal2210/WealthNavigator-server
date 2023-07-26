package com.hemalpatel.wealthnavigator.configuration.security.jwt;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class JwtSecretGenerator {

    private static final String charStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int secretLength = 256;
    private static String jwtSecret = "";

    private JwtSecretGenerator() {
    }

    public static String getJwtSecret() {
        if (jwtSecret.isEmpty()) {
            try {
                SecureRandom secureRandom = SecureRandom.getInstanceStrong();
                jwtSecret = secureRandom.ints(secretLength, 0, charStr.length())
                        .mapToObj(charStr::charAt)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        return jwtSecret;
    }
}
