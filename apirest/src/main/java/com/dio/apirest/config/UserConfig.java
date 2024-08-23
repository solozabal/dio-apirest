package com.dio.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * Configuration class for user management in the application.
 * 
 * This class configures an in-memory user details service and a password encoder
 * using the BCrypt algorithm. It is responsible for providing a default user for authentication.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@Configuration
public class UserConfig {

    /**
     * Creates a UserDetailsService bean that manages users in memory.
     * 
     * This method defines a default user with username "user", password "password"
     * (encoded with BCrypt), and the role "USER". The user is stored in an in-memory
     * user details manager.
     * 
     * @param passwordEncoder The password encoder to be used for encoding the user's password.
     * @return A UserDetailsService object that manages users in memory.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("password")) // Encodes the password with BCrypt
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Creates a PasswordEncoder bean using the BCrypt algorithm.
     * 
     * This method provides a password encoder that can be used to
     * securely encode passwords.
     * 
     * @return A PasswordEncoder object that uses the BCrypt algorithm.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean for BCryptPasswordEncoder
    }
}