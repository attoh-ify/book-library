package org.example.utils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.SecureRandom;
import java.util.Base64;

public class PasswordUtils {
    private static final int ITERATIONS = 12000;
    private static final int KEY_LENGTH = 256;

    private PasswordUtils() {}

    public static String hashPassword(String password) {
        try {
            byte[] salt = generateSalt();
            byte[] hashed = pbkdf2(password.toCharArray(), salt);

            return Base64.getEncoder().encodeToString(salt) + ":" +
                    Base64.getEncoder().encodeToString(hashed);
        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean verifyPassword(String password, String storedHash) {
        try {
            String[] parts = storedHash.split(":");
            byte[] salt = Base64.getDecoder().decode(parts[0]);
            byte[] correctHash = Base64.getDecoder().decode(parts[1]);

            byte[] testHash = pbkdf2(password.toCharArray(), salt);

            if (testHash.length != correctHash.length) return false;

            for (int i = 0; i < testHash.length; i++) {
                if (testHash[i] != correctHash[i]) return false;
            }

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static byte[] pbkdf2(char[] password, byte[] salt) throws Exception {
        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS, KEY_LENGTH);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
        return skf.generateSecret(spec).getEncoded();
    }

    private static byte[] generateSalt() {
        byte[] salt = new byte[16];
        new SecureRandom().nextBytes(salt);
        return salt;
    }
}