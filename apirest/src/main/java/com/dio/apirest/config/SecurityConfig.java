package com.dio.apirest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Classe de configuração de segurança para a aplicação.
 * 
 * Esta classe configura as regras de segurança da aplicação, incluindo
 * a proteção de rotas específicas e a configuração de autenticação básica.
 * 
 * @author Pedro Solozabal
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    /**
     * Configura o filtro de segurança da aplicação.
     * 
     * Este método define as regras de segurança, como a desabilitação do
     * CSRF, a proteção de rotas específicas que requerem autenticação e
     * a configuração do método de autenticação básica.
     * 
     * @param http O objeto HttpSecurity que permite a configuração de
     *             segurança da aplicação.
     * @return Um objeto SecurityFilterChain configurado.
     * @throws Exception Se ocorrer um erro durante a configuração da segurança.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Desabilita CSRF, caso necessário
            .authorizeHttpRequests(auth -> auth // Usando authorizeHttpRequests() com Lambda DSL
                .requestMatchers("/api/person/**").authenticated() // Protege a rota /api/person/** com autenticação
                .anyRequest().permitAll() // Qualquer outra rota é permitida
            )
            .httpBasic(customizer -> customizer.realmName("MyApp")); // Configuração do httpBasic com customização

        return http.build();
    }
}