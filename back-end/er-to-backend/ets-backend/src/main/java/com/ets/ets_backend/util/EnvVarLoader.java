package com.ets.ets_backend.util;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.stereotype.Component;

@Component
public class EnvVarLoader {

    private static final Dotenv dotenv = Dotenv.configure().directory("../../postgres-container").load();

    public static void loadEnvVars() {
        System.setProperty("POSTGRES_HOST", dotenv.get("POSTGRES_HOST"));
        System.setProperty("POSTGRES_PORT", dotenv.get("POSTGRES_PORT"));
        System.setProperty("POSTGRES_DB", dotenv.get("POSTGRES_DB"));
        System.setProperty("POSTGRES_USER", dotenv.get("POSTGRES_USER"));
        System.setProperty("POSTGRES_PASSWORD", dotenv.get("POSTGRES_PASSWORD"));
    }
}
