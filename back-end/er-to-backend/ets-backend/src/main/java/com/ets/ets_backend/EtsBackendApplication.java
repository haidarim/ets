package com.ets.ets_backend;

import com.ets.ets_backend.util.EnvVarLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


/**
 * The entry point
 * */
@SpringBootApplication
public class EtsBackendApplication {

	public static void main(String[] args) {


		// Load environment variables
		EnvVarLoader.loadEnvVars();
		SpringApplication.run(EtsBackendApplication.class, args);
	}

}
