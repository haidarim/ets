package com.ets.ets_backend.config;


import com.ets.ets_backend.security.ETSUserDetailService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/**
 *  Configuration class to set up the authentication security configuration.
 *  This class uses also the {@code @EnableWebSecurity} which enables Spring Security's web security
 *  and provides the MVC integration.
 *
 * @author  Mehdi Haidari
 * */

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Autowired
    private ETSUserDetailService etsUserDetailService;


    /**
     * The bean below (returned object) will be used by the Spring Boot container to define how
     * HTTP security is handled. This method set up the filter chain e.g.
     * @param http: HttpSecurity,
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf(AbstractHttpConfigurer::disable) // Disable the CSRF protection
                .authorizeHttpRequests(authorizeRequests->
                    authorizeRequests // Starts the configuration for URL-based authorization
                            .requestMatchers("/api0/sign-in/**").permitAll() // allow unauthenticated access to this path
                            .anyRequest().authenticated() // require authentication for any other request
                ).sessionManagement(
                        // Setting the Session management to stateless i.e. NO session will be created or
                        // used by Spring Security,
                        sessionManagement->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }


    /**
     * TODO
     * */
    @Bean
    public AuthenticationManager authenticationManager(){
        // TODO ...
    }
}
