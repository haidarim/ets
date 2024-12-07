package com.orelease.etc.util;

import io.github.cdimascio.dotenv.Dotenv;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
/*
@Component
public class EnvVarLoader {

    private static final Dotenv dotenv = Dotenv.configure().directory("./src/main/resources/sec").load();

    @PostConstruct
    public static void loadEnvVars() {
        System.setProperty("JWT_PR_KEY", dotenv.get("JWT_PR_KEY"));
        System.setProperty("JWT_PU_KEY", dotenv.get("JWT_PU_KEY"));
    }

    public static void main(String[] args) {
        loadEnvVars();

        System.out.println("JWT_PR_KEY: " + System.getProperty("POSTGRES_PASSWORD"));
        System.out.println("JWT_PU_KEY: " + System.getProperty("POSTGRES_PASSWORD"));
    }
}*/
