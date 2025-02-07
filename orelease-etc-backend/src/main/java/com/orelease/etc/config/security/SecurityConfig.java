package com.orelease.etc.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
    private ETSUserDetailsService etsUserDetailService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

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
                            .requestMatchers("/api0/sign-up").permitAll() // allow unauthenticated access to this path
                            .anyRequest().authenticated() // require authentication for any other request
                ).sessionManagement(
                        // Setting the Session management to stateless i.e. NO session will be created or
                        // used by Spring Security,
                        sessionManagement->
                                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    /**
     * Configures and returns an AuthenticationManager
     * @param http: HttpSecurity
     * @param  passwordEncoder: PasswordEncode
     * @throws Exception if configuration goes wrong
     * */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder ab =  http.getSharedObject(AuthenticationManagerBuilder.class);
        ab.userDetailsService(etsUserDetailService)
                .passwordEncoder(passwordEncoder);
        return ab.build();
    }


    /**
     * @return BCryptPasswordEncode as PasswordEncoder for hashing passwords
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        //return new HashUtil();
        return new BCryptPasswordEncoder();
    }

}
