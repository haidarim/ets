package com.orelease.etc.config.security;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

@Component
public class HashUtil implements PasswordEncoder {
    private final String GENERAL_PEPPER = "somak";

    public byte[] generateSalt(int length){
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[length];
        sr.nextBytes(salt);
        return salt;
    }

    public String saltAndPepperPassword(String password, byte[] salt){
        password = password + GENERAL_PEPPER;
        // Concatenate salt bytes with password bytes
        return Base64.getEncoder().encodeToString(salt) + password;
    }

    @Override
    public String encode(CharSequence saltedPassword) {
        Argon2 argon2 = Argon2Factory.create();

        // hash with 10 rounds, 65536 memory in kb, 1 parallelism (single-threaded)
        return argon2.hash(10, 65536, 1, saltedPassword.toString());
    }

    @Override
    public boolean matches(CharSequence password, String encodedPassword) {
        Argon2 argon2 = Argon2Factory.create();
        System.out.println("in verifying!!!<---------------------------");
        // Verify the hash
        return argon2.verify(encodedPassword, password.toString());
    }
}
