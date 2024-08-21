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
 * Classe de configuração para gerenciamento de usuários na aplicação.
 * 
 * Esta classe configura um serviço de detalhes do usuário em memória e um codificador de senha
 * utilizando o algoritmo BCrypt. É responsável por fornecer um usuário padrão para autenticação.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@Configuration
public class UserConfig {

    /**
     * Cria um bean de UserDetailsService que gerencia usuários em memória.
     * 
     * Este método define um usuário padrão com nome de usuário "user", senha "password"
     * (codificada com BCrypt) e a função "USER". O usuário é armazenado em um gerenciador
     * de detalhes do usuário em memória.
     * 
     * @param passwordEncoder O codificador de senha a ser utilizado para codificar a senha do usuário.
     * @return Um objeto UserDetailsService que gerencia usuários em memória.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
        UserDetails user = User.withUsername("user")
            .password(passwordEncoder.encode("password")) // Codifica a senha com BCrypt
            .roles("USER")
            .build();
        return new InMemoryUserDetailsManager(user);
    }

    /**
     * Cria um bean de PasswordEncoder utilizando o algoritmo BCrypt.
     * 
     * Este método fornece um codificador de senha que pode ser utilizado para
     * codificar senhas de forma segura.
     * 
     * @return Um objeto PasswordEncoder que utiliza o algoritmo BCrypt.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Bean para BCryptPasswordEncoder
    }
}