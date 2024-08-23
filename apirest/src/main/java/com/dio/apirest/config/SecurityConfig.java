package com.dio.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration class for the application.
 * 
 * This class configures the security rules of the application, including
 * the protection of specific routes and the configuration of basic authentication.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configures the security filter chain for the application.
     * 
     * This method defines the security rules, such as disabling CSRF,
     * protecting specific routes that require authentication, and
     * configuring basic authentication.
     * 
     * @param http The HttpSecurity object that allows the configuration of
     *             the application's security.
     * @return A configured SecurityFilterChain object.
     * @throws Exception If an error occurs during security configuration.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disables CSRF, if necessary
            .authorizeHttpRequests(auth -> auth // Using authorizeHttpRequests() with Lambda DSL
                .requestMatchers("/api/person/**").authenticated() // Protects the /api/person/** route with authentication
                .anyRequest().permitAll() // Any other route is permitted
            )
            .httpBasic(customizer -> customizer.realmName("MyApp")); // Configures httpBasic with customization

        return http.build();
    }
}