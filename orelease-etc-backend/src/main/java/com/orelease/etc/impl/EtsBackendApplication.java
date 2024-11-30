package com.orelease.etc.impl;

import com.orelease.etc.util.EnvVarLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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
