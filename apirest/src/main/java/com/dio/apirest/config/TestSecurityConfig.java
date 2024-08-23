package com.dio.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Security configuration for tests.
 * 
 * This class disables CSRF protection and permits all HTTP requests,
 * ensuring that security does not interfere with tests.
 * 
 * <p>This configuration is used only for the testing environment and should not be
 * used in production.</p>
 * 
 * <p>To configure security, the class defines a bean of type
 * {@link SecurityFilterChain} which is responsible for applying security
 * configurations to {@link HttpSecurity}.</p>
 * 
 * @see SecurityFilterChain
 * @see HttpSecurity
 */
@Configuration
public class TestSecurityConfig {

    /**
     * Configures the security filter chain.
     * 
     * <p>This method disables CSRF protection and permits all HTTP requests,
     * ensuring that security does not interfere with tests.</p>
     * 
     * @param http the {@link HttpSecurity} object to configure
     * @return the configured {@link SecurityFilterChain}
     * @throws Exception if an error occurs while configuring security
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());
        return http.build();
    }
}