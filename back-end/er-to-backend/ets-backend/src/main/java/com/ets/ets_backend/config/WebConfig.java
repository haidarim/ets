package com.ets.ets_backend.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * To enable Cross-Origin Resource Sharing (CORS) on the Spring Boot backend.
 * CORS allows the server to specify who can access its resources.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        //  addMapping("/**"): Defines which endpoints (URL patterns) CORS should be applied to.
        //  In this case, /** means apply CORS to all endpoints.


        registry.addMapping("/**")
                .allowedOrigins("*") // allow all origins (domains)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Allowed methods
                .allowedHeaders("*");
    }
}
