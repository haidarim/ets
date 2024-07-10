package com.ets.ets_backend.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class HashUtil {
    private static final String PEPPER = "somak";

    public static String hashPassword(String password, byte[] salt){
        password = password + PEPPER;

        Argon2 argon2 = Argon2Factory.create();

        // Concatenate salt bytes with password bytes
        String saltedPassword = Base64.getEncoder().encodeToString(salt) + password;

        // hash with 10 rounds, 65536 memory in kb, 1 parallelism (single-threaded)
        return argon2.hash(10, 65536, 1, saltedPassword.toCharArray());
    }

    public static byte[] generateSalt(int length){
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[length];
        sr.nextBytes(salt);
        return salt;
    }

    public static boolean verifyPassword(String storedHash, String password, byte[] salt){
        password = password + PEPPER;

        Argon2 argon2 = Argon2Factory.create();

        // Concatenate salt bytes with password bytes
        String saltedPassword = Base64.getEncoder().encodeToString(salt) + password;

        // Verify the hash
        return argon2.verify(storedHash, saltedPassword.toCharArray());
    }

}
