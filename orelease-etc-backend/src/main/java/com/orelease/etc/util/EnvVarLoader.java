package com.orelease.etc.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvVarLoader {

    private static final Dotenv dotenv = Dotenv.configure().directory("./orelease-etc-config").load();

    public static void loadEnvVars() {
        System.setProperty("POSTGRES_HOST", dotenv.get("POSTGRES_HOST"));
        System.setProperty("POSTGRES_PORT", dotenv.get("POSTGRES_PORT"));
        System.setProperty("POSTGRES_DB", dotenv.get("POSTGRES_DB"));
        System.setProperty("POSTGRES_USER", dotenv.get("POSTGRES_USER"));
        System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD"));
        System.setProperty("JWT_PR_KEY", dotenv.get("JWT_PR_KEY"));
        System.setProperty("JWT_PU_KEY", dotenv.get("JWT_PU_KEY"));
    }

    /*public static void main(String[] args) {
        loadEnvVars();
        System.out.println("POSTGRES_HOST: " + System.getProperty("POSTGRES_HOST"));
        System.out.println("POSTGRES_PORT: " + System.getProperty("POSTGRES_PORT"));
        System.out.println("POSTGRES_DB: " + System.getProperty("POSTGRES_DB"));
        System.out.println("POSTGRES_USER: " + System.getProperty("POSTGRES_USER"));
        System.out.println("POSTGRES_PASSWORD: " + System.getProperty("POSTGRES_PASSWORD"));
    }*/
}
